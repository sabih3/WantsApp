package wantapp.netaq.com.wantapp.screens.chat_screen;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.listeners.QBChatDialogTypingListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.ChatAdapter;
import wantapp.netaq.com.wantapp.db.managers.MessageDataManager;
import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;
import wantapp.netaq.com.wantapp.db.models.Message;
import wantapp.netaq.com.wantapp.utils.NavigationController;
import wantapp.netaq.com.wantapp.utils.PreferencesManager;
import wantapp.netaq.com.wantapp.utils.UIUtils;

public class ScreenChat extends AppCompatActivity {

    @BindView(R.id.chat_list)RecyclerView chatList;
    @BindView(R.id.messgae_editor)EditText chatEditor;
    @BindView(R.id.send_button) ImageView btnSend;
    @BindView(R.id.toolbar_with_logo)Toolbar toolbar;
    @BindView(R.id.typing_status)GifImageView typingGIF;

    private ChatAdapter adapter;
    private QBChatDialog qbChatDialog;
    private Dialog dialog;
    private List<Message> chatMessagesList;
    private boolean typingStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_chat);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);


        qbChatDialog = (QBChatDialog) getIntent().getSerializableExtra(NavigationController.EXTRA_DIALOG);
        qbChatDialog.initForChat(QBChatService.getInstance());
        dialog = MessageDataManager.getLocalDialog(qbChatDialog);

        PreferencesManager.getInstance().setActiveDialogID(qbChatDialog.getDialogId());

        initToolbar();

        btnSend.setOnClickListener(new SendButtonListener());

        chatMessagesList = MessageDataManager.getAllMessages(dialog);

        adapter = new ChatAdapter(chatMessagesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScreenChat.this);
        linearLayoutManager.setStackFromEnd(true);
        chatList.setLayoutManager(linearLayoutManager);
        chatList.setAdapter(adapter);
        PreferencesManager.getInstance().setChatIsActive(true);
        chatEditor.addTextChangedListener(new ChatTypingWatcher());

        qbChatDialog.addIsTypingListener(new OpponentUserTypingListener());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recreate();
    }

    @Override
    protected void onStop() {
        super.onStop();

        PreferencesManager.getInstance().setChatIsActive(false);
    }

    private void initToolbar() {


        String title = dialog.getTitle();

        setSupportActionBar(UIUtils.adjustToolbar(this,toolbar,title));

    }

    private class SendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String messageToSend = chatEditor.getText().toString();

            if(!messageToSend.isEmpty()){

                    final QBChatMessage chatMessage = new QBChatMessage();
                    chatMessage.setBody(messageToSend);
                    chatMessage.setSaveToHistory(true);
                    chatMessage.setRecipientId(qbChatDialog.getRecipientId());


                final Message persistedMessage = MessageDataManager.persistChatMessage(dialog, chatMessage);

                qbChatDialog.sendMessage(chatMessage, new QBEntityCallback<Void>() {
                        @Override
                        public void onSuccess(Void aVoid, Bundle bundle) {

                            chatEditor.setText("");

                            chatMessagesList.add(chatMessagesList.size(),persistedMessage);
                            adapter.notifyItemInserted(chatMessagesList.size());
                            chatList.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Call smooth scroll
                                    chatList.smoothScrollToPosition(adapter.getItemCount());
                                }
                            });


                        }

                        @Override
                        public void onError(QBResponseException e) {

                        }
                    });


            }

        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActiveChatProcess(ActiveChatEvent activeChatEvent){
        if(qbChatDialog.getDialogId().equals(activeChatEvent.getDialogID())){
            Message persistedMessage = MessageDataManager.persistChatMessage(dialog, activeChatEvent.getQbChatMessage());
            chatMessagesList.add(chatMessagesList.size(),persistedMessage);
            adapter.notifyItemInserted(chatMessagesList.size());
            chatList.smoothScrollToPosition(adapter.getItemCount());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        PreferencesManager.getInstance().setActiveDialogID("");
    }

    private class ChatTypingWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editText) {
            if (!TextUtils.isEmpty(editText.toString()) && editText.toString().trim().length() == 1) {

                //Log.i(TAG, “typing started event…”);
                typingStarted = true;

                sendTypingStatus(typingStarted);

            } else if (editText.toString().trim().length() == 0 && typingStarted) {

                //Log.i(TAG, “typing stopped event…”);
                typingStarted = false;
                sendTypingStatus(typingStarted);

            }

        }
    }

    private void sendTypingStatus(boolean typingStarted) {
        if(typingStarted){
            try {
                qbChatDialog.sendIsTypingNotification();
            } catch (XMPPException | SmackException.NotConnectedException e) {
                e.printStackTrace();
            }
        }else{
            try {
                qbChatDialog.sendStopTypingNotification();
            } catch (XMPPException | SmackException.NotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    private class OpponentUserTypingListener implements QBChatDialogTypingListener {
        @Override
        public void processUserIsTyping(String s, Integer integer) {
            typingGIF.setVisibility(View.VISIBLE);
        }

        @Override
        public void processUserStopTyping(String s, Integer integer) {
            typingGIF.setVisibility(View.GONE);
        }
    }
}


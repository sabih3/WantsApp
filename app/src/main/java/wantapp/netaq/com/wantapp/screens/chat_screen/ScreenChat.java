package wantapp.netaq.com.wantapp.screens.chat_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.ChatAdapter;
import wantapp.netaq.com.wantapp.db.managers.MessageDataManager;
import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;
import wantapp.netaq.com.wantapp.db.models.Message;
import wantapp.netaq.com.wantapp.utils.NavigationController;

public class ScreenChat extends AppCompatActivity {

    @BindView(R.id.chat_list)RecyclerView chatList;
    @BindView(R.id.messgae_editor)EditText chatEditor;
    @BindView(R.id.send_button) ImageView btnSend;
    private ChatAdapter adapter;

    private QBChatDialog qbChatDialog;
    private Dialog dialog;
    private List<Message> chatMessagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_chat);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        qbChatDialog = (QBChatDialog) getIntent().getSerializableExtra(NavigationController.EXTRA_DIALOG);
        qbChatDialog.initForChat(QBChatService.getInstance());
        dialog = MessageDataManager.getLocalDialog(qbChatDialog);

        QBRequestGetBuilder requestBuilder = new QBRequestGetBuilder();
        requestBuilder.setLimit(100);

        btnSend.setOnClickListener(new SendButtonListener());

        chatMessagesList = MessageDataManager.getAllMessages(dialog);

        adapter = new ChatAdapter(chatMessagesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScreenChat.this);
        linearLayoutManager.setStackFromEnd(true);
        chatList.setLayoutManager(linearLayoutManager);
        chatList.setAdapter(adapter);
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
        Message persistedMessage = MessageDataManager.persistChatMessage(dialog, activeChatEvent.getQbChatMessage());
        chatMessagesList.add(chatMessagesList.size(),persistedMessage);
        adapter.notifyItemInserted(chatMessagesList.size());
        chatList.smoothScrollToPosition(adapter.getItemCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

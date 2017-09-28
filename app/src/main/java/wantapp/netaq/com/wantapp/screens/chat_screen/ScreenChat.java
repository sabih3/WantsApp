package wantapp.netaq.com.wantapp.screens.chat_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBIncomingMessagesManager;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.exception.QBChatException;
import com.quickblox.chat.listeners.QBChatDialogMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.muc.DiscussionHistory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.ChatAdapter;
import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;
import wantapp.netaq.com.wantapp.utils.NavigationController;

public class ScreenChat extends AppCompatActivity {

    @BindView(R.id.chat_list)RecyclerView chatList;
    @BindView(R.id.messgae_editor)EditText chatEditor;
    @BindView(R.id.send_button) ImageView btnSend;
    private ChatAdapter adapter;

    private QBChatDialog qbChatDialog;
    private ArrayList<QBChatMessage> chatMessagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_chat);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        qbChatDialog = (QBChatDialog) getIntent().getSerializableExtra(NavigationController.EXTRA_DIALOG);
        Log.d("Printing QBChatDialog",qbChatDialog.toString());
        qbChatDialog.initForChat(QBChatService.getInstance());


        QBRequestGetBuilder requestBuilder = new QBRequestGetBuilder();
        requestBuilder.setLimit(100);

        btnSend.setOnClickListener(new SendButtonListener());


        QBRestChatService.getDialogMessages(qbChatDialog,requestBuilder).performAsync(new QBEntityCallback<ArrayList<QBChatMessage>>() {
            @Override
            public void onSuccess(ArrayList<QBChatMessage> qbChatMessages, Bundle bundle) {
                chatMessagesList = qbChatMessages;
                adapter = new ChatAdapter(chatMessagesList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScreenChat.this);
                linearLayoutManager.setStackFromEnd(true);
                chatList.setLayoutManager(linearLayoutManager);
                chatList.setAdapter(adapter);


            }

            @Override
            public void onError(QBResponseException e) {

            }
        });



    }

    private class SendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String messageToSend = chatEditor.getText().toString();

            if(!messageToSend.isEmpty()){
                try {

                    QBChatMessage chatMessage = new QBChatMessage();
                    chatMessage.setBody(messageToSend);
                    chatMessage.setSaveToHistory(true);
                    chatMessage.setRecipientId(qbChatDialog.getRecipientId());
                    qbChatDialog.sendMessage(chatMessage);

                    chatEditor.setText("");

                    chatMessagesList.add(chatMessagesList.size(),chatMessage);
                    adapter.notifyItemInserted(chatMessagesList.size());
                    chatList.post(new Runnable() {
                        @Override
                        public void run() {
                            // Call smooth scroll
                            chatList.smoothScrollToPosition(adapter.getItemCount());
                        }
                    });

                } catch (SmackException.NotConnectedException e) {

                }
            }



        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActiveChatProcess(ActiveChatEvent activeChatEvent){
        chatMessagesList.add(chatMessagesList.size(),activeChatEvent.getQbChatMessage());
        adapter.notifyItemInserted(chatMessagesList.size());
        chatList.smoothScrollToPosition(adapter.getItemCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

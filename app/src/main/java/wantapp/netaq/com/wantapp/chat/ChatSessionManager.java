package wantapp.netaq.com.wantapp.chat;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBIncomingMessagesManager;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.exception.QBChatException;
import com.quickblox.chat.listeners.QBChatDialogMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.utils.DialogUtils;
import com.quickblox.core.QBEntityCallback;

import com.quickblox.core.StoringMechanism;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;
import wantapp.netaq.com.wantapp.eventbus.ChatDialogCreatedEvent;
import wantapp.netaq.com.wantapp.utils.ChatUtils;
import wantapp.netaq.com.wantapp.utils.NotificationHelper;
import wantapp.netaq.com.wantapp.utils.QBUserHelper;

/**
 * Created by sabih on 24-Sep-17.
 */

public class ChatSessionManager {

    private static ChatSessionManager chatSessionManager;
    private static Context context;
    private boolean login;
    private ChatSessionManager() {

    }

    public static synchronized ChatSessionManager getInstance(){
        if(chatSessionManager == null){

            chatSessionManager = new ChatSessionManager();
        }

        return chatSessionManager;
    }

    public void init(Context context){
        this.context = context;
    }

    /**Call no 1
     *
     */
    public void initializeChatSession(){
        String APP_ID = "62721";
        String AUTH_KEY = "n-W2h5kEvHQOxFj";
        String AUTH_SECRET = "wdCcZ5VMEy8wmCu";
        String ACCOUNT_KEY = "XzExLYHmpyvVBQXPSKxM";

        QBSettings.getInstance().setStoringMehanism(StoringMechanism.UNSECURED);
        QBSettings.getInstance().init(context, APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);


        QBAuth.createSession();
    }

    public boolean login(QBUser qbUser, final QBLoginListener qbLoginListener){
        QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {

                qbUser.setId(qbUser.getId());
                qbUser.setPassword(qbUser.getLogin());
                loginChatService(qbUser,qbLoginListener);
            }

            @Override
            public void onError(QBResponseException e) {
                qbLoginListener.onChatLoginFailure(e);
            }
        });

        return login;

    }


    public void loginChatService(QBUser qbUser, final QBLoginListener qbLoginListener){

        final QBChatService chatService = QBChatService.getInstance();
        chatService.setConfigurationBuilder(ChatUtils.getChatServiceConfiguration());

        chatService.login(qbUser, new QBEntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {

                qbLoginListener.onLoggedInChat();

                QBIncomingMessagesManager incomingMessagesManager = chatService.getIncomingMessagesManager();
                incomingMessagesManager.addDialogMessageListener(new QBChatDialogMessageListener() {
                    @Override
                    public void processMessage(String s, QBChatMessage qbChatMessage, Integer integer) {
                        EventBus.getDefault().post(new ActiveChatEvent(s,qbChatMessage,integer));
                        NotificationHelper.notify(context,qbChatMessage.getBody(),"");
                    }

                    @Override
                    public void processError(String s, QBChatException e, QBChatMessage qbChatMessage, Integer integer) {

                    }
                });

            }

            @Override
            public void onError(QBResponseException e) {
                qbLoginListener.onChatLoginFailure(e);
            }
        });



    }

    public void createChat(int participantID) {

        //or just use DialogUtils
        //for creating PRIVATE dialog
        QBChatDialog dialog = DialogUtils.buildPrivateDialog(participantID);
        if(!QBChatService.getInstance().isLoggedIn()){

            Toast.makeText(context, "Chat Service Not Logged In", Toast.LENGTH_SHORT).show();

        }else{
            QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
                @Override
                public void onSuccess(QBChatDialog dialog, Bundle params) {

                    EventBus.getDefault().post(new ChatDialogCreatedEvent(dialog,params));

                }

                @Override
                public void onError(QBResponseException responseException) {

                }
            });
        }

    }

    public void getChatDialogs(final ChatDialogsListener chatDialogsListener){
        //QBChatService chatService = QBChatService.getInstance();
        QBRequestGetBuilder requestBuilder = new QBRequestGetBuilder();
        requestBuilder.setLimit(100);

        QBRestChatService.getChatDialogs(null, requestBuilder).performAsync(
                new QBEntityCallback<ArrayList<QBChatDialog>>() {
                    @Override
                    public void onSuccess(ArrayList<QBChatDialog> result, Bundle params) {
                        int totalEntries = params.getInt("total_entries");
                        chatDialogsListener.onSuccess(result);
                    }

                    @Override
                    public void onError(QBResponseException responseException) {
                        chatDialogsListener.onError(responseException);
                    }
                });
    }

    public interface ChatDialogsListener{
        void onSuccess(ArrayList<QBChatDialog> dialogsList);
        void onError(QBResponseException responseException);
    }

    public interface QBLoginListener {

        void onLoggedInChat();
        void onChatLoginFailure(QBResponseException e);
    }
}

package wantapp.netaq.com.wantapp.chat;

import android.content.Context;
import android.os.Bundle;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.auth.session.QBSessionManager;
import com.quickblox.auth.session.QBSessionParameters;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBIncomingMessagesManager;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.exception.QBChatException;
import com.quickblox.chat.listeners.QBChatDialogMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.chat.utils.DialogUtils;
import com.quickblox.core.QBEntityCallback;

import com.quickblox.core.StoringMechanism;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import org.greenrobot.eventbus.EventBus;
import org.jivesoftware.smack.SmackException;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;
import wantapp.netaq.com.wantapp.eventbus.ChatDialogCreatedEvent;
import wantapp.netaq.com.wantapp.utils.NotificationHelper;

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



    }

    public boolean login(){
        final QBUser azam = new QBUser("muhammad.azam@netaq.ae","netaq123");

        final QBUser refaat = new QBUser("muhammed.refaat@netaq.ae","netaq123");

        final QBUser sabih = new QBUser("sabih.ahmed@netaq.ae", "netaq123");

        QBUsers.signIn(sabih).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                qbUser.setId(qbUser.getId());
                qbUser.setPassword("netaq123");
                loginChatService(qbUser);
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });


        return login;

    }


    public void loginChatService(QBUser qbUser){
        //final QBUser user = new QBUser("sabih.ahmed@netaq.ae", "netaq123");
        // Initialise Chat service
        final QBChatService chatService = QBChatService.getInstance();

        //Set it before login in chat
        QBChatService.ConfigurationBuilder builder = new QBChatService.ConfigurationBuilder();
        builder.setAutojoinEnabled(true);
        builder.setSocketTimeout(180); //Sets chat socket's read timeout in seconds
        builder.setKeepAlive(true);
        chatService.setConfigurationBuilder(builder);

        chatService.login(qbUser, new QBEntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {


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

            }
        });



    }

    public void createChat(int participantID) {

        //or just use DialogUtils
        //for creating PRIVATE dialog
        QBChatDialog dialog = DialogUtils.buildPrivateDialog(participantID);

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
}

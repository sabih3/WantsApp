package wantapp.netaq.com.wantapp.screens.chat_list;

import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.core.exception.QBResponseException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;
import wantapp.netaq.com.wantapp.db.managers.DBManager;
import wantapp.netaq.com.wantapp.db.managers.DialogDataManager;
import wantapp.netaq.com.wantapp.db.models.Dialog;

/**
 * Created by sabih on 24-Sep-17.
 */

public class ChatListPresenter {

    private ScreenChatList chatListView;


    public ChatListPresenter(ScreenChatList chatListView) {
        this.chatListView = chatListView;
    }


    public boolean setChatList() {

        return false;
    }

    public void checkUserHasPastChats() {
        //call quick blox api to get user past chats, if it has, call chatListView.initializeChatList();
        //if it does not have, call chatListView.showNoChatListView();
        chatListView.showProgressView();

        List<Dialog> pastDialogs = DialogDataManager.getPastDialogs();

        if(pastDialogs.isEmpty() ){
            ChatSessionManager.getInstance().getChatDialogs(new ChatSessionManager.ChatDialogsListener() {
                @Override
                public void onSuccess(ArrayList<QBChatDialog> dialogsList) {

                    if(dialogsList != null){
                        if(dialogsList.size()>0){
                            List<Dialog> freshlyPersistedDialogs = DialogDataManager .persistPastDialogs(dialogsList);
                            chatListView.initializeChatList(freshlyPersistedDialogs);
                        }
                        else{
                            chatListView.showNoChatListView();
                        }
                    }else{
                        chatListView.showNoChatListView();
                    }
                }

                @Override
                public void onError(QBResponseException responseException) {

                }
            });
        }

        else{
            chatListView.initializeChatList(pastDialogs);
        }



    }

    public void createChatWithUserID(int userID) {
        ChatSessionManager.getInstance().createChat(userID);
    }
}

package wantapp.netaq.com.wantapp.screens.chat_list;

import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.core.exception.QBResponseException;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;

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


        ChatSessionManager.getInstance().getChatDialogs(new ChatSessionManager.ChatDialogsListener() {
            @Override
            public void onSuccess(ArrayList<QBChatDialog> dialogsList) {

                if(dialogsList != null){
                    if(dialogsList.size()>0){
                        chatListView.initializeChatList(dialogsList);
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

    public void createChatWithUserID(int userID) {
        ChatSessionManager.getInstance().createChat(userID);
    }
}

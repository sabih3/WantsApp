package wantapp.netaq.com.wantapp.screens.chat_list;

import com.quickblox.chat.model.QBChatDialog;

import java.util.ArrayList;

/**
 * Created by sabih on 24-Sep-17.
 */

public interface ChatListView {

    void initializeChatList(ArrayList<QBChatDialog> dialogsList);
    void showNoChatListView();

    void showProgressView();
}

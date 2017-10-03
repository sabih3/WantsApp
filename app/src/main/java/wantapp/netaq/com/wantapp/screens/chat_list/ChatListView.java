package wantapp.netaq.com.wantapp.screens.chat_list;

import java.util.List;

import wantapp.netaq.com.wantapp.db.models.Dialog;

/**
 * Created by sabih on 24-Sep-17.
 */

public interface ChatListView {

    void initializeChatList(List<Dialog> dialogsList);
    void showNoChatListView();

    void showProgressView();
}

package wantapp.netaq.com.wantapp.eventbus;

import android.os.Bundle;

import com.quickblox.chat.model.QBChatDialog;

/**
 * Created by sabih on 26-Sep-17.
 */

public class ChatDialogCreatedEvent {

    private Bundle params;
    private QBChatDialog chatDialog;

    public ChatDialogCreatedEvent(QBChatDialog dialog, Bundle params) {
        this.chatDialog = dialog;
        this.params = params;
    }

    public Bundle getParams() {
        return params;
    }

    public QBChatDialog getChatDialog() {
        return chatDialog;
    }
}

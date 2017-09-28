package wantapp.netaq.com.wantapp.eventbus;

import com.quickblox.chat.model.QBChatMessage;

/**
 * Created by sabih on 26-Sep-17.
 */

public class ActiveChatEvent {

    private final String dialogID;
    private final QBChatMessage qbChatMessage;
    private final Integer senderID;

    public ActiveChatEvent(String dialogID, QBChatMessage qbChatMessage, Integer senderID) {
        this.dialogID = dialogID;
        this.qbChatMessage = qbChatMessage;
        this.senderID = senderID;
    }

    public String getDialogID() {
        return dialogID;
    }

    public QBChatMessage getQbChatMessage() {
        return qbChatMessage;
    }

    public Integer getSenderID() {
        return senderID;
    }
}

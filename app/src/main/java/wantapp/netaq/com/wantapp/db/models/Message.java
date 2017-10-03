package wantapp.netaq.com.wantapp.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import static wantapp.netaq.com.wantapp.db.models.Message.Column.BODY;
import static wantapp.netaq.com.wantapp.db.models.Message.Column.DATE_SENT;
import static wantapp.netaq.com.wantapp.db.models.Message.Column.DIALOG_ID;
import static wantapp.netaq.com.wantapp.db.models.Message.Column.ID;
import static wantapp.netaq.com.wantapp.db.models.Message.Column.RECIPIENT_ID;
import static wantapp.netaq.com.wantapp.db.models.Message.Column.SENDER_ID;
import static wantapp.netaq.com.wantapp.db.models.Message.Column.TABLE_NAME;

/**
 * Created by sabih on 30-Sep-17.
 */


@DatabaseTable(tableName = TABLE_NAME)
public class Message implements Serializable{

    @DatabaseField(id = true,
                    unique = true,
                    columnName = ID)
    private String messageID;

    @DatabaseField(foreign = true,
                   foreignAutoRefresh = true,
                    canBeNull = false,
                    columnName = Dialog.Column.ID)
    private Dialog dialog;

    @DatabaseField(columnName = BODY)
    private String body;

    @DatabaseField(columnName = DATE_SENT)
    private long dateSent;

    @DatabaseField(columnName = RECIPIENT_ID)
    private int recipientID;

    @DatabaseField(columnName = SENDER_ID)
    private int senderID;

    public Message() {

    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDateSent(long dateSent) {
        this.dateSent = dateSent;
    }

    public void setRecipientID(int recipientID) {
        this.recipientID = recipientID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getMessageID() {
        return messageID;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public String getBody() {
        return body;
    }

    public long getDateSent() {
        return dateSent;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public int getSenderID() {
        return senderID;
    }

    public interface Column{

        String TABLE_NAME = "messages";
        String ID = "message_id";
        String DIALOG_ID = "dialog_id";
        String BODY = "body";
        String DATE_SENT = "date_sent";
        String RECIPIENT_ID = "recipient_id";
        String SENDER_ID = "sender_id";


    }
}

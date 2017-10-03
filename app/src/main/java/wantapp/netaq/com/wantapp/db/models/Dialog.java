package wantapp.netaq.com.wantapp.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.ID;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.LAST_MESSAGE;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.MODIFIED_DATE_LOCAL;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.PHOTO;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.RECIPIENT_ID;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.ROOM_JID;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.TABLE_NAME;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.TITLE;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.TYPE;
import static wantapp.netaq.com.wantapp.db.models.Dialog.Column.UPDATED_AT;

/**
 * Created by sabih on 28-Sep-17.
 */

@DatabaseTable(tableName = TABLE_NAME)
public class Dialog implements Serializable{


    @DatabaseField(id = true,
                   unique = true,
                   columnName = ID)
    private String dialogId;

    @DatabaseField(columnName = TYPE)
    private Type type;

    @DatabaseField(columnName = ROOM_JID)
    private String roomJid;

    @DatabaseField(columnName = TITLE)
    private String title;

    @DatabaseField(columnName = PHOTO)
    private String photo;

    @DatabaseField(columnName = MODIFIED_DATE_LOCAL)
    private long modifiedDateLocal;

    @DatabaseField(columnName = UPDATED_AT)
    private long updateAt;

    @DatabaseField(columnName = LAST_MESSAGE)
    private String lastMessage;

    @DatabaseField(columnName = RECIPIENT_ID )
    private Integer recipientId;


    public Dialog() {

    }

    public String getDialogId() {
        return dialogId;
    }

    public Type getType() {
        return type;
    }

    public String getRoomJid() {
        return roomJid;
    }

    public String getTitle() {
        return title;
    }

    public String getPhoto() {
        return photo;
    }

    public long getModifiedDateLocal() {
        return modifiedDateLocal;
    }

    public long getUpdateAt() {
        return updateAt;
    }


    public String getLastMessage() {
        return lastMessage;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setRoomJid(String roomJid) {
        this.roomJid = roomJid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setModifiedDateLocal(long modifiedDateLocal) {
        this.modifiedDateLocal = modifiedDateLocal;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Dialog [dialogId='" + dialogId
                + "', modifiedDateLocal='" + modifiedDateLocal
                + "', title='" + title + "']";
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }


    public enum Type {
        PUBLIC_GROUP(1),
        GROUP(2),
        PRIVATE(3);

        private int code;

        Type(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public interface Column{
        String TABLE_NAME = "dialog";
        String ID = "dialog_id";
        String ROOM_JID = "room_jid";
        String TITLE = "title";
        String PHOTO = "photo";
        String TYPE = "type";
        String MODIFIED_DATE_LOCAL = "modified_date_local";
        String UPDATED_AT = "updated_at";
        String LAST_MESSAGE = "last_message";
        String RECIPIENT_ID = "recipient_id";
    }
}

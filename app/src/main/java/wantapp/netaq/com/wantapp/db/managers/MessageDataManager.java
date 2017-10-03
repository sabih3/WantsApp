package wantapp.netaq.com.wantapp.db.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.db.models.Message;

/**
 * Created by sabih on 01-Oct-17.
 */

public class MessageDataManager {


    public static List<Message> getAllMessages(Dialog dialog){
        List<Message> allMessages = new ArrayList<>();
        Dao<Message, Integer> messageDao = DBManager.getInstance().getDatabaseHelper().getMessageDao();
        QueryBuilder<Message, Integer> messageQueryBuilder = DBManager.getInstance().getDatabaseHelper().getMessageDao().queryBuilder();
        try {
            messageQueryBuilder.where().eq(Message.Column.DIALOG_ID,dialog.getDialogId());
            PreparedQuery<Message> queryForAllMessages = messageQueryBuilder.prepare();

            allMessages = messageDao.query(queryForAllMessages);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allMessages;

    }


    public static Dialog getLocalDialog(QBChatDialog qbChatDialog){
        Dialog dialog = null;

        try {
            Dao<Dialog, Integer> dialogDAO = DialogDataManager.getDialogDAO();
            QueryBuilder<Dialog, Integer> queryBuilder = dialogDAO.queryBuilder();
            queryBuilder.where().eq(Dialog.Column.ID, qbChatDialog.getDialogId());
            PreparedQuery<Dialog> preparedQuery = queryBuilder.prepare();
            dialog = dialogDAO.queryForFirst(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public static Message persistChatMessage(Dialog dialog, QBChatMessage chatMessage){
        Dialog Localdialog = dialog;

        Localdialog.setLastMessage(chatMessage.getBody());
        try {
            DBManager.getInstance().getDatabaseHelper().getDialogDao().createOrUpdate(Localdialog);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Message message = new Message();
        message.setMessageID(chatMessage.getId());
        message.setDialog(Localdialog);
        message.setSenderID(QBChatService.getInstance().getUser().getId());
        message.setRecipientID(chatMessage.getRecipientId());
        message.setBody(chatMessage.getBody());
        message.setDateSent(System.currentTimeMillis());

        Message persistedMessage = null;
        try {
            DBManager.getInstance().getDatabaseHelper().getMessageDao().createOrUpdate(message);
            persistedMessage = DBManager.getInstance().getDatabaseHelper().getMessageDao().queryForSameId(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persistedMessage;
    }
    
    public static Message getLastMessage(){
        Message lastMessage = null;
        
        try {
            Dao<Message, Integer> messageDao = DBManager.getInstance().getDatabaseHelper().getMessageDao();
            QueryBuilder<Message, Integer> messageQueryBuilder = messageDao.queryBuilder();
            Message message = messageQueryBuilder.queryForFirst();

            lastMessage = message;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastMessage;
    }
}

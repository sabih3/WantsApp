package wantapp.netaq.com.wantapp.db.managers;

import com.j256.ormlite.dao.Dao;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialogType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wantapp.netaq.com.wantapp.db.helpers.DatabaseHelper;
import wantapp.netaq.com.wantapp.db.models.Dialog;

/**
 * Created by sabih on 30-Sep-17.
 */

public class DialogDataManager {


    public static Dao<Dialog, Integer> getDialogDAO(){
        Dao<Dialog, Integer> dialogDao = DBManager.getInstance().getDatabaseHelper().getDialogDao();

        return dialogDao;
    }
    public static List<Dialog> getPastDialogs(){
        List<Dialog> dialogs = new ArrayList<>();

        try {
            dialogs = DBManager.getInstance().getDatabaseHelper().getDialogDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dialogs;
    }

    public static List<Dialog> persistPastDialogs(ArrayList<QBChatDialog> dialogsList) {
        List<Dialog> freshPersistedDialogs = new ArrayList<>();

        DatabaseHelper databaseHelper = DBManager.getInstance().getDatabaseHelper();
        for(QBChatDialog chatDialog : dialogsList){
            Dialog dialog = new Dialog();

            dialog.setDialogId(chatDialog.getDialogId());
            dialog.setRoomJid(chatDialog.getRoomJid());
            dialog.setTitle(chatDialog.getName());
            dialog.setLastMessage(chatDialog.getLastMessage());
            dialog.setRecipientId(chatDialog.getRecipientId());

            if(chatDialog.getType()== QBDialogType.PRIVATE){
                dialog.setType(Dialog.Type.PRIVATE);
            }
            if(chatDialog.getType() == QBDialogType.GROUP){
                dialog.setType(Dialog.Type.GROUP);
            }

            dialog.setPhoto(chatDialog.getPhoto());
            //dialog.setUpdateAt(chatDialog.getUpdatedAt());
            try {
                databaseHelper.getDialogDao().createOrUpdate(dialog);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            freshPersistedDialogs = databaseHelper.getDialogDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return freshPersistedDialogs;
    }


    public static void persistNewCreatedDialog(QBChatDialog chatDialog) {
        DatabaseHelper databaseHelper = DBManager.getInstance().getDatabaseHelper();

        Dialog dialog = new Dialog();

        dialog.setDialogId(chatDialog.getDialogId());
        dialog.setRoomJid(chatDialog.getRoomJid());
        dialog.setTitle(chatDialog.getName());
        dialog.setLastMessage(chatDialog.getLastMessage());
        dialog.setRecipientId(chatDialog.getRecipientId());

        if(chatDialog.getType()== QBDialogType.PRIVATE){
            dialog.setType(Dialog.Type.PRIVATE);
        }
        if(chatDialog.getType() == QBDialogType.GROUP){
            dialog.setType(Dialog.Type.GROUP);
        }

        dialog.setPhoto(chatDialog.getPhoto());
        //dialog.setUpdateAt(chatDialog.getUpdatedAt());
        try {
            databaseHelper.getDialogDao().createOrUpdate(dialog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

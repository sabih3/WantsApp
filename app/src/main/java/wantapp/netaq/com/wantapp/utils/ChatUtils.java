package wantapp.netaq.com.wantapp.utils;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.chat.utils.DialogUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wantapp.netaq.com.wantapp.db.managers.DBManager;
import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.db.models.Message;

/**
 * Created by sabih on 30-Sep-17.
 */

public class ChatUtils {

    public static QBChatDialog transformDialog(Dialog dialog) {
        QBChatDialog qbChatDialog = new QBChatDialog();
        List<Integer> occupantIdsList = new ArrayList<>();

        occupantIdsList.add(QBChatService.getInstance().getUser().getId());
        occupantIdsList.add(dialog.getRecipientId());

        qbChatDialog.setDialogId(dialog.getDialogId());
        qbChatDialog.setOccupantsIds(occupantIdsList);
        qbChatDialog.setPhoto(dialog.getPhoto());
        qbChatDialog.setName(dialog.getTitle());
        qbChatDialog.setType(Dialog.Type.PRIVATE.equals(dialog.getType()) ? QBDialogType.PRIVATE : QBDialogType.GROUP);

        return qbChatDialog;
    }

    public static QBChatService.ConfigurationBuilder getChatServiceConfiguration(){
        //Set it before login in chat
        QBChatService.ConfigurationBuilder builder = new QBChatService.ConfigurationBuilder();
        builder.setAutojoinEnabled(true);
        builder.setSocketTimeout(180); //Sets chat socket's read timeout in seconds
        builder.setKeepAlive(true);

        return builder;
    }
}

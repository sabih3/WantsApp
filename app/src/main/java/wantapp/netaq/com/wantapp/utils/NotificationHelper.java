package wantapp.netaq.com.wantapp.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.provider.Settings;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.db.managers.DialogDataManager;
import wantapp.netaq.com.wantapp.db.managers.MessageDataManager;
import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.screens.chat_screen.ScreenChat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by sabih on 28-Sep-17.
 */

public class NotificationHelper {

    public static void notify(final Context context, final QBChatMessage chatMessage, final String dialogID){

        final Dialog localDialog = MessageDataManager.getLocalDialog(dialogID);

        if(localDialog != null){

            issueNotification(context,localDialog,chatMessage.getBody());

        }else{
            QBRestChatService.getChatDialogById(dialogID).performAsync(
                    new QBEntityCallback<QBChatDialog>() {
                        @Override
                        public void onSuccess(QBChatDialog dialog, Bundle params) {
                            DialogDataManager.persistNewCreatedDialog(dialog);

                            Dialog newlyCreatedDialog = MessageDataManager.getLocalDialog(dialog.getDialogId());

                            MessageDataManager.persistChatMessage(newlyCreatedDialog, chatMessage);

                            issueNotification(context,newlyCreatedDialog,chatMessage.getBody());
                        }

                        @Override
                        public void onError(QBResponseException responseException) {

                        }
                    });

        }


    }


    public static void issueNotification(Context context,Dialog localDialog,String message){
        QBChatDialog qbChatDialog = ChatUtils.transformDialog(localDialog);

        Intent chatScreenIntent = new Intent(context, ScreenChat.class);
        chatScreenIntent.putExtra(NavigationController.EXTRA_DIALOG,qbChatDialog);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,chatScreenIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(localDialog.getTitle())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));


        int mNotificationId = 001;

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}

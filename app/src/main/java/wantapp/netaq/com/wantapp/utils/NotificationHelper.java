package wantapp.netaq.com.wantapp.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import wantapp.netaq.com.wantapp.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by sabih on 28-Sep-17.
 */

public class NotificationHelper {

    public static String CHANNEL_ID_DEFAULT = "1";

    public static void notify(Context context,String message,String dialogID){

//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.mipmap.ic_launcher_round)
//                        .setContentTitle("WantsApp")
//                        .setContentText(message)
//                        .setChannelId(CHANNEL_ID_DEFAULT)
//                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI
//                        );
//
//
//        int mNotificationId = 001;
//
//        NotificationManager mNotifyMgr =
//                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        // Builds the notification and issues it.
//        mNotifyMgr.notify(mNotificationId, mBuilder.build());

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID_DEFAULT)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setChannelId(CHANNEL_ID_DEFAULT)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, mBuilder.build());

    }
}

package wantapp.netaq.com.wantapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;
import wantapp.netaq.com.wantapp.db.helpers.DatabaseHelper;
import wantapp.netaq.com.wantapp.db.managers.DBManager;
import wantapp.netaq.com.wantapp.utils.NotificationHelper;
import wantapp.netaq.com.wantapp.utils.PreferencesManager;

/**
 * Created by sabih on 24-Sep-17.
 */

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.init(this);
        DBManager.getInstance().getDatabaseHelper().getWritableDatabase();

        PreferencesManager.getInstance().init(this);
        ChatSessionManager.getInstance().init(this);
        ChatSessionManager.getInstance().initializeChatSession();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registerNotificationChannel() {
        CharSequence name = "Default Channel";
        String description = "iWant Description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(NotificationHelper.CHANNEL_ID_DEFAULT, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}

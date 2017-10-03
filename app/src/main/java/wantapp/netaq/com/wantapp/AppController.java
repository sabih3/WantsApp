package wantapp.netaq.com.wantapp;

import android.app.Application;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;
import wantapp.netaq.com.wantapp.db.helpers.DatabaseHelper;
import wantapp.netaq.com.wantapp.db.managers.DBManager;
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
}

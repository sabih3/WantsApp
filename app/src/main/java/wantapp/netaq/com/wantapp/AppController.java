package wantapp.netaq.com.wantapp;

import android.app.Application;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;

/**
 * Created by sabih on 24-Sep-17.
 */

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ChatSessionManager.getInstance();
        ChatSessionManager.getInstance().init(this);
        ChatSessionManager.getInstance().initializeChatSession();
        ChatSessionManager.getInstance().login();

    }
}

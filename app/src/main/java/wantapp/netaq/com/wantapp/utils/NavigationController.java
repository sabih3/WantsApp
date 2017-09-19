package wantapp.netaq.com.wantapp.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import wantapp.netaq.com.wantapp.screens.ScreenCreateProfile;
import wantapp.netaq.com.wantapp.screens.ScreenNewWant;
import wantapp.netaq.com.wantapp.screens.ScreenSignIn;

/**
 * Created by sabih on 17-Sep-17.
 */

public class NavigationController {

    public static void showNewWantScreen(Context context) {

        Intent intent = new Intent(context,ScreenNewWant.class);
        context.startActivity(intent);
    }

    public static void showCreateProfileScreen(Context context) {

        Intent intent = new Intent(context,ScreenCreateProfile.class);
        context.startActivity(intent);
    }

    public static void showSignInScreen(Context context) {
        Intent intent = new Intent(context,ScreenSignIn.class);
        context.startActivity(intent);
    }
}

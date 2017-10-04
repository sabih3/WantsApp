package wantapp.netaq.com.wantapp.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.screens.MainActivity;
import wantapp.netaq.com.wantapp.screens.chat_list.ScreenChatList;
import wantapp.netaq.com.wantapp.screens.temp_login.TempLoginFragment;
import wantapp.netaq.com.wantapp.screens.alerts.ScreenAddAlert;
import wantapp.netaq.com.wantapp.screens.ScreenCreateProfile;
import wantapp.netaq.com.wantapp.screens.needs.ScreenNewWant;
import wantapp.netaq.com.wantapp.screens.ScreenOTP;
import wantapp.netaq.com.wantapp.screens.register.ScreenRegister;
import wantapp.netaq.com.wantapp.screens.ScreenSignIn;

/**
 * Created by sabih on 17-Sep-17.
 */

public class NavigationController {

    public static final String EXTRA_DIALOG = "chatDialog";

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

    public static void showAddAlertScreen(Context context) {
        Intent intent = new Intent(context,ScreenAddAlert.class);
        context.startActivity(intent);

    }

    public static void showRegistrationFragment(FragmentManager supportFragmentManager) {

        ScreenRegister registerFragment = new ScreenRegister();
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,registerFragment,"").commit();
    }

    public static void showVerificationScreen(FragmentManager supportFragmentManager) {
        ScreenOTP otpFragment = new ScreenOTP();
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,otpFragment,"").commit();

    }

    public static void showLoginFragment(FragmentManager supportFragmentManager) {
        TempLoginFragment loginFragment = new TempLoginFragment();
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,loginFragment,"").commit();
    }

    public static void showConsumerChatListScreen(Context context) {
        Intent intent = new Intent(context,ScreenChatList.class);
        context.startActivity(intent);


    }

    public static void showMainScreen(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}

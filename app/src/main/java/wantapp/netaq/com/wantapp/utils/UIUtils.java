package wantapp.netaq.com.wantapp.utils;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

/**
 * Created by sabih on 04-Oct-17.
 */

public class UIUtils {

    public static void showSnackbar(CoordinatorLayout loginParent, String msg) {

        Snackbar.make(loginParent,msg,Snackbar.LENGTH_SHORT).show();
    }
}

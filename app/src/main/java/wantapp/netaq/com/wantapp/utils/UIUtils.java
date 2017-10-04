package wantapp.netaq.com.wantapp.utils;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 04-Oct-17.
 */

public class UIUtils {

    public static void showSnackbar(CoordinatorLayout loginParent, String msg) {

        Snackbar.make(loginParent,msg,Snackbar.LENGTH_SHORT).show();
    }

    public static void showProgress(View rootView) {
        try {
            View progress = rootView.findViewById(R.id.progress);
            progress.setVisibility(View.VISIBLE);
        }catch (NullPointerException npe){

        }
    }

    public static void hideProgress(View rootView) {
        try {
            View progress = rootView.findViewById(R.id.progress);
            progress.setVisibility(View.INVISIBLE);
        }catch (NullPointerException npe){

        }


    }
}

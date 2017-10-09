package wantapp.netaq.com.wantapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.screens.MainActivity;
import wantapp.netaq.com.wantapp.screens.chat_list.ScreenChatList;
import wantapp.netaq.com.wantapp.screens.needs.ScreenNewWant;

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

    public static Toolbar adjustToolbar(Context context, Toolbar toolbar) {

        Toolbar processedToolbar = toolbar;
        processedToolbar.setTitle("");
        if(context instanceof MainActivity){

            processedToolbar.findViewById(R.id.back_icon).setVisibility(View.INVISIBLE);
            processedToolbar.findViewById(R.id.cancel_text).setVisibility(View.INVISIBLE);

        }

        if(context instanceof ScreenNewWant){
            processedToolbar.findViewById(R.id.back_icon).setVisibility(View.INVISIBLE);
        }

        if(context instanceof ScreenChatList){
            processedToolbar.findViewById(R.id.cancel_text).setVisibility(View.INVISIBLE);
        }

        return processedToolbar;
    }

    public static void showMessageDialog(Context context, String message, String positiveButtonTitle,
                                         String negativeButtonTitle,
                                         final DialogButtonListener dialogButtonListener) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);


        builder.setMessage(message).
                setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogButtonListener.onPositiveButtonClicked();
                    }
                }).setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                dialogButtonListener.onNegativeButtonClicked();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));


    }

    public interface DialogButtonListener {
        void onPositiveButtonClicked();
        void onNegativeButtonClicked();


    }
}

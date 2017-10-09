package wantapp.netaq.com.wantapp.utils;

import android.view.View;

/**
 * Created by sabih on 07-Oct-17.
 */

public class ToolbarInteractionListener implements View.OnClickListener {
    private final onToolbarActions listener;

    public ToolbarInteractionListener(onToolbarActions listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onToolbarCancelClick();
    }


    public interface onToolbarActions {
        void onToolbarCancelClick();
    }
}

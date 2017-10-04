package wantapp.netaq.com.wantapp.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 04-Oct-17.
 */

public class AlertListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.alert_label)public TextView alertLabel;

    public AlertListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}


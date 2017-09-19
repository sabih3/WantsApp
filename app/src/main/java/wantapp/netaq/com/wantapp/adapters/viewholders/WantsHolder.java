package wantapp.netaq.com.wantapp.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 18-Sep-17.
 */

public class WantsHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.row_want_label)public TextView wantsLabel;

    public WantsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

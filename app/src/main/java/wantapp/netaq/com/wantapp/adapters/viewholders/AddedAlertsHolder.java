package wantapp.netaq.com.wantapp.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 19-Sep-17.
 */

public class AddedAlertsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.chip_text)public TextView chipsText;
    @BindView(R.id.chip_delete)public ImageView btnChipDelete;

    public AddedAlertsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

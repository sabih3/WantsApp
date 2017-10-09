package wantapp.netaq.com.wantapp.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 25-Sep-17.
 */


public class ChatHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.parent_chat_message)public FrameLayout chatMessageParent;
    @BindView(R.id.tv_chat_message)public TextView chatMessage;
    @BindView(R.id.chat_image)public CircleImageView userImage;

    public ChatHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

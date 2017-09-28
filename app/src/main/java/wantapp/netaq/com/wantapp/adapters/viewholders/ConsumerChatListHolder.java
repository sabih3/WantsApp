package wantapp.netaq.com.wantapp.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rockerhieu.emojicon.EmojiconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 25-Sep-17.
 */

public class ConsumerChatListHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_last_msg)public EmojiconTextView tvLastMessage;
    @BindView(R.id.tv_name)public TextView tvName;
    @BindView(R.id.tv_unread_msg)public TextView tvUnread;

    public ConsumerChatListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

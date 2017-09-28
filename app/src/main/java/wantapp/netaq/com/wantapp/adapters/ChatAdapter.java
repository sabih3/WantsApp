package wantapp.netaq.com.wantapp.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatMessage;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.viewholders.ChatHolder;

/**
 * Created by sabih on 25-Sep-17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder>{

    private ArrayList<QBChatMessage> mDataset;
    private Context mContext;
    public ChatAdapter(ArrayList<QBChatMessage> qbChatMessages) {
        this.mDataset = qbChatMessages;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_chat, parent, false);

        ChatHolder holder = new ChatHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {

        QBChatMessage qbChatMessage = mDataset.get(position);

        if(!isMine(position)) {

            //show this message right aligned
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.chatMessage.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            holder.chatMessage.setLayoutParams(layoutParams);

        }

        holder.chatMessage.setText(qbChatMessage.getBody());
    }

    //check if current user is the recipient
    private boolean isMine(int position) {
        if(mDataset.get(position).getRecipientId().equals(QBChatService.getInstance().getUser().getId())){

            return true;
        }

        return false;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}

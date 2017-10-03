package wantapp.netaq.com.wantapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.quickblox.chat.QBChatService;

import java.text.SimpleDateFormat;
import java.util.List;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.viewholders.ChatHolder;
import wantapp.netaq.com.wantapp.db.models.Message;

/**
 * Created by sabih on 25-Sep-17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder>{

    private List<Message> mDataset;
    private Context mContext;
    public ChatAdapter(List<Message> qbChatMessages) {
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

        Message chatMessage = mDataset.get(position);

        if(!isMine(position)) {

            //show this message right aligned
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.chatMessage.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            holder.chatMessage.setLayoutParams(layoutParams);

        }



        holder.chatMessage.setText(chatMessage.getBody());
        getMessageTime(chatMessage);



    }

    private String getMessageTime(Message chatMessage) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String time = "";
        long dateSent = chatMessage.getDateSent();

        if(DateUtils.isToday(dateSent)){

            String relativeTime = DateUtils.getRelativeTimeSpanString(dateSent).toString();
            time = relativeTime;
        }else{
            String formattedDate = dateFormat.format(dateSent);
            time = formattedDate;

        }

        return time;

    }

    //check if current user is the recipient
    private boolean isMine(int position) {
        if(mDataset.get(position).getRecipientID()==(QBChatService.getInstance().getUser().getId())){

            return true;
        }

        return false;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}

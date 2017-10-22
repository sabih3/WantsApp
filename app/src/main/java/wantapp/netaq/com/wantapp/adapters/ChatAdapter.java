package wantapp.netaq.com.wantapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quickblox.chat.QBChatService;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.db.models.Message;

/**
 * Created by sabih on 25-Sep-17.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VIEW_TYPE_MSG_RECEIVED = 2;
    private static final int VIEW_TYPE_MSG_SENT = 1;
    private List<Message> mDataset;
    private Context mContext;
    public ChatAdapter(List<Message> qbChatMessages) {
        this.mDataset = qbChatMessages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        if(viewType == VIEW_TYPE_MSG_RECEIVED){

            View row = LayoutInflater.from(mContext).inflate(R.layout.row_message_received,parent,false);

            return new ReceivedMessageHolder(row);

        }if(viewType == VIEW_TYPE_MSG_SENT){
            View row = LayoutInflater.from(mContext).inflate(R.layout.row_message_sent,parent,false);
            return new SentMessageHolder(row);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        //if chat msg recipient id is same as logged is user id, means that it is received msg

        if(mDataset.get(position).getRecipientID()==(QBChatService.getInstance().getUser().getId())){

            return VIEW_TYPE_MSG_RECEIVED;
        }else{
            return VIEW_TYPE_MSG_SENT;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Message chatMessage = mDataset.get(position);

        switch (holder.getItemViewType()){
            case VIEW_TYPE_MSG_RECEIVED:
                ((ReceivedMessageHolder)holder).bind(chatMessage);
            break;

            case VIEW_TYPE_MSG_SENT:
                ((SentMessageHolder)holder).bind(chatMessage);
            break;
        }
        /**if(!isMine(position)) {
            //show this message right aligned
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.chatMessage.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.chatMessage.setLayoutParams(layoutParams);
        }

        holder.chatMessage.setText(chatMessage.getBody());
        holder.userImage.animate().translationX(30f);
        holder.userImage.animate().translationY(30f);
        holder.userImage.bringToFront();
        getMessageTime(chatMessage);**/

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


    public class SentMessageHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sent_msg_body)TextView sentMessageBody;
        @BindView(R.id.sent_msg_time)TextView sentMsgTime;

        public SentMessageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Message chatMessage) {
            sentMessageBody.setText(chatMessage.getBody());
            sentMsgTime.setText(getMessageTime(chatMessage));
        }
    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.msg_img)public CircleImageView userImage;
        @BindView(R.id.msg_user_name)public TextView userName;
        @BindView(R.id.sent_msg_body)public TextView sentMsgBody;
        @BindView(R.id.rcvd_msg_time)public TextView receivedMsgTime;

        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Message chatMessage) {
            //Picasso.with(mContext).load()
            userName.setText(Integer.toString(chatMessage.getSenderID()));
            sentMsgBody.setText(chatMessage.getBody());
            receivedMsgTime.setText(getMessageTime(chatMessage));
        }
    }
}

package wantapp.netaq.com.wantapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quickblox.chat.model.QBChatDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.viewholders.ConsumerChatListHolder;
import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;

/**
 * Created by sabih on 25-Sep-17.
 */

public class ConsumerChatListAdapter extends RecyclerView.Adapter<ConsumerChatListHolder> {

    private ArrayList<QBChatDialog> mDataset;
    private Context mContext;
    private ConsumerDialogListener dialogListener;
    private ConsumerChatListHolder mHolder;

    public void setDialogListener(ConsumerDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public ConsumerChatListAdapter(ArrayList<QBChatDialog> dialogsList) {
        this.mDataset = dialogsList;

        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void updateDialog(ActiveChatEvent event){
        for(QBChatDialog dialog : mDataset){
            if(dialog.getDialogId().equals(event.getDialogID())){
                //update unread count
                mHolder.tvLastMessage.setText("");
                mHolder.tvLastMessage.setText(event.getQbChatMessage().getBody());
                mHolder.tvLastMessage.setTypeface(mHolder.tvLastMessage.getTypeface(), Typeface.BOLD);
                mHolder.tvUnread.setVisibility(View.VISIBLE);
            }else{
                mHolder.tvUnread.setVisibility(View.INVISIBLE);
            }
        }

    }
    @Override
    public ConsumerChatListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_consumer_chat_list, parent, false);

        ConsumerChatListHolder holder = new ConsumerChatListHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(ConsumerChatListHolder holder, int position) {

        this.mHolder = holder;

        final QBChatDialog chatDialog = mDataset.get(position);

        mHolder.tvLastMessage.setText(chatDialog.getLastMessage());

        mHolder.tvName.setText(chatDialog.getName());



        mHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.onChatDialogClick(chatDialog);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset==null ? 0 : mDataset.size();
    }

    public interface ConsumerDialogListener{

        void onChatDialogClick(QBChatDialog chatDialog);
    }
}

package wantapp.netaq.com.wantapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.viewholders.AlertListHolder;
import wantapp.netaq.com.wantapp.models.Alerts;
import wantapp.netaq.com.wantapp.screens.alerts.ScreenAlertsList;

/**
 * Created by sabih on 04-Oct-17.
 */

public class AlertsListAdapter extends RecyclerView.Adapter<AlertListHolder>{


    private ArrayList<Alerts> mDataset;
    private Context mContext;
    private AlertClickListener alertClickListener;

    public void setListClickListener(AlertClickListener alertClickListener) {

        this.alertClickListener = alertClickListener;
    }
    public AlertsListAdapter(ArrayList<Alerts> alertsList) {
        this.mDataset = alertsList;
    }

    @Override
    public AlertListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_alert_list, parent, false);
        AlertListHolder holder = new AlertListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlertListHolder holder, int position) {

        holder.alertLabel.setText(mDataset.get(position).getName());

        holder.rowParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertClickListener.onListItemClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    public interface AlertClickListener{

        void onListItemClick();
    }
}

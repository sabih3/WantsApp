package wantapp.netaq.com.wantapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.viewholders.AddedAlertsHolder;

/**
 * Created by sabih on 19-Sep-17.
 */

public class AddedAlertsAdapter extends RecyclerView.Adapter<AddedAlertsHolder>{

    private ArrayList<String> mDataset;

    public AddedAlertsAdapter(ArrayList<String> addedAlertList) {
        this.mDataset = addedAlertList;
    }

    @Override
        public AddedAlertsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.chips_layout, parent,false);

            AddedAlertsHolder holder = new AddedAlertsHolder(row);
            return holder;
        }

        @Override
        public void onBindViewHolder(AddedAlertsHolder holder, int position) {

            holder.chipsText.setText(mDataset.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataset.isEmpty() ? 0 : mDataset.size();
    }
}


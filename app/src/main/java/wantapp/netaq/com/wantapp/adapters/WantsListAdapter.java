package wantapp.netaq.com.wantapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.viewholders.WantsHolder;
import wantapp.netaq.com.wantapp.models.Want;

/**
 * Created by Sabih on 18-Sep-17.
 */

public class WantsListAdapter extends RecyclerView.Adapter<WantsHolder>{

    private ArrayList<Want> mDataset;

    public WantsListAdapter(ArrayList<Want> wantsList) {
        this.mDataset = wantsList;
    }

    @Override
    public WantsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_wants_list, parent, false);

        WantsHolder wantsHolder = new WantsHolder(row);

        return wantsHolder;
    }

    @Override
    public void onBindViewHolder(WantsHolder holder, int position) {

        holder.wantsLabel.setText(mDataset.get(position).getWant());

        if(position%2 !=0){
            holder.checked.setImageResource(R.drawable.shape_circle_green);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}

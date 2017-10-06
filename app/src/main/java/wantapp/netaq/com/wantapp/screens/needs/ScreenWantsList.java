package wantapp.netaq.com.wantapp.screens.needs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.WantsListAdapter;
import wantapp.netaq.com.wantapp.utils.NavigationController;
import wantapp.netaq.com.wantapp.utils.WantsContainer;


public class ScreenWantsList extends Fragment {

    @BindView(R.id.add_new_want)FloatingActionButton addWantButton;
    @BindView(R.id.wants_label)TextView wantsLabel;
    @BindView(R.id.needs_empty_view)LinearLayout emptyView;
    @BindView(R.id.list_wants)RecyclerView wantsList;

    public ScreenWantsList() {
        // Required empty public constructor
    }


    public static ScreenWantsList newInstance() {
        ScreenWantsList fragment = new ScreenWantsList();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wants_list, container, false);

        ButterKnife.bind(this,view);

        initViews();
        return view;
    }

    private void initViews() {
        addWantButton.setOnClickListener(new AddWantButton());
        wantsLabel.setOnClickListener(new AddWantButton());

        setWantsList();
    }

    private void setWantsList() {

        if(WantsContainer.wantsList.size() > 0){
            emptyView.setVisibility(View.GONE);
            wantsList.setVisibility(View.VISIBLE);

            wantsList.setLayoutManager(new LinearLayoutManager(getContext()));
            WantsListAdapter wantsAdapter = new WantsListAdapter(WantsContainer.wantsList);

            wantsList.setAdapter(wantsAdapter);
        }else{
            emptyView.setVisibility(View.VISIBLE);
            wantsList.setVisibility(View.GONE);
        }


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class AddWantButton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            NavigationController.showNewWantScreen(getActivity());
        }
    }
}

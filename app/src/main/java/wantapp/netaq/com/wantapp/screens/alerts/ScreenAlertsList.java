package wantapp.netaq.com.wantapp.screens.alerts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.AlertsListAdapter;
import wantapp.netaq.com.wantapp.models.Alerts;
import wantapp.netaq.com.wantapp.utils.NavigationController;

public class ScreenAlertsList extends Fragment implements AlertsView,AlertsListAdapter.AlertClickListener{

    @BindView(R.id.add_new_alert) FloatingActionButton addAlertButton;
    @BindView(R.id.alerts_recycler)RecyclerView alertsList;

    private OnFragmentInteractionListener mListener;
    private ScreenAlertsPresenter alertsPresenter;
    public ScreenAlertsList() {

    }

    public static ScreenAlertsList newInstance() {
        ScreenAlertsList fragment = new ScreenAlertsList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_alerts_list, container, false);
        ButterKnife.bind(this,view);
        alertsPresenter = new ScreenAlertsPresenter(this);
        initViews();
        return view;

    }

    private void initViews() {

        addAlertButton.setOnClickListener(new AddAlertListener());
        alertsPresenter.setAlertsList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAlertsListReady(ArrayList<Alerts> alerts) {
        setList(alerts);
    }

    private void setList(ArrayList<Alerts> alerts) {
        AlertsListAdapter alertsAdapter = new AlertsListAdapter(alerts);

        alertsList.setLayoutManager(new LinearLayoutManager(getContext()));

        alertsList.setAdapter(alertsAdapter);

        alertsAdapter.setListClickListener(this);


    }

    @Override
    public void onListItemClick() {

        NavigationController.showConsumerChatListScreen(getContext());
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private class AddAlertListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            NavigationController.showAddAlertScreen(getActivity());
            //NavigationController.showCreateProfileScreen(getActivity());
        }
    }
}

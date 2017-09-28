package wantapp.netaq.com.wantapp.screens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.utils.NavigationController;

public class AlertsList extends Fragment {

    @BindView(R.id.add_new_alert) FloatingActionButton addAlertButton;
    private OnFragmentInteractionListener mListener;

    public AlertsList() {

    }

    public static AlertsList newInstance() {
        AlertsList fragment = new AlertsList();
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

        initViews();
        return view;

    }

    private void initViews() {
        addAlertButton.setOnClickListener(new AddAlertListener());
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
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

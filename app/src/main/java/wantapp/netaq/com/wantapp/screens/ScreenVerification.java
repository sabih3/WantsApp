package wantapp.netaq.com.wantapp.screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wantapp.netaq.com.wantapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenVerification extends Fragment {


    public ScreenVerification() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.screen_verification, container, false);
    }

}

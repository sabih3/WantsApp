package wantapp.netaq.com.wantapp.screens.register;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.FragmentPagerAdapter;
import wantapp.netaq.com.wantapp.models.FragmentContainer;
import wantapp.netaq.com.wantapp.screens.login.ScreenLogin;


public class RegistrationHostFragment extends Fragment {


    @BindView(R.id.login_pager)ViewPager pager;
    @BindView(R.id.login_tabs)TabLayout tabs;
    public RegistrationHostFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration_host, container, false);

        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
        ScreenLogin loginFragment = new ScreenLogin();
        ScreenRegister registerFragment = new ScreenRegister();

        FragmentContainer loginScreen = new FragmentContainer(loginFragment, "Login");
        FragmentContainer registerContainer = new FragmentContainer(registerFragment, "Register");
        ArrayList<FragmentContainer> fragmentList = new ArrayList<>();
        fragmentList.add(loginScreen);
        fragmentList.add(registerContainer);

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        pager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(pager);
    }

}

package wantapp.netaq.com.wantapp.screens;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.FragmentPagerAdapter;
import wantapp.netaq.com.wantapp.models.FragmentContainer;
import wantapp.netaq.com.wantapp.screens.AlertsList;
import wantapp.netaq.com.wantapp.screens.ScreenWantsList;

public class MainActivity extends AppCompatActivity implements AlertsList.OnFragmentInteractionListener{

    @BindView(R.id.tabs)TabLayout tabs;
    @BindView(R.id.pager)ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recreate();
    }

    private void initViews() {
        setTabs();
    }

    private void setTabs() {

        FragmentContainer wantsFragment = new FragmentContainer(ScreenWantsList.newInstance(),"Your Needs");
        FragmentContainer needsFragment = new FragmentContainer(AlertsList.newInstance(),"Your Alerts");

        ArrayList fragmentList = new ArrayList();
        fragmentList.add(wantsFragment);
        fragmentList.add(needsFragment);

        PagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(),fragmentList);

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

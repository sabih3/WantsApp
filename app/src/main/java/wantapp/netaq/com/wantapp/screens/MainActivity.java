package wantapp.netaq.com.wantapp.screens;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.FragmentPagerAdapter;
import wantapp.netaq.com.wantapp.models.FragmentContainer;
import wantapp.netaq.com.wantapp.screens.alerts.ScreenAlertsList;
import wantapp.netaq.com.wantapp.screens.needs.ScreenWantsList;
import wantapp.netaq.com.wantapp.utils.UIUtils;

public class MainActivity extends AppCompatActivity implements ScreenAlertsList.OnFragmentInteractionListener{

    @BindView(R.id.tabs)TabLayout tabs;
    @BindView(R.id.pager)ViewPager pager;
    @BindView(R.id.toolbar_with_logo)Toolbar toolbar;

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
        setToolbar();
        setTabs();
    }

    private void setToolbar() {


        setSupportActionBar(UIUtils.adjustToolbar(MainActivity.this,toolbar,""));
    }

    private void setTabs() {

        FragmentContainer wantsFragment = new FragmentContainer(ScreenWantsList.newInstance(),"Your Needs");
        FragmentContainer needsFragment = new FragmentContainer(ScreenAlertsList.newInstance(),"Your Alerts");

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

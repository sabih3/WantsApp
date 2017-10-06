package wantapp.netaq.com.wantapp.screens.register;

import android.support.design.widget.TabLayout;
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
import wantapp.netaq.com.wantapp.screens.login.ScreenLogin;
import wantapp.netaq.com.wantapp.screens.register.ScreenRegister;
import wantapp.netaq.com.wantapp.utils.NavigationController;

public class ScreenRegistrationHost extends AppCompatActivity {


    @BindView(R.id.toolbar)Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_registration_host);
        ButterKnife.bind(this);
        initViews();
        setToolbar();
    }

    private void setToolbar() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
    }

    private void initViews() {

        NavigationController.showRegistrationHostFragment(getSupportFragmentManager());

    }
}

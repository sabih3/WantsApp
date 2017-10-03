package wantapp.netaq.com.wantapp.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.utils.NavigationController;

public class ScreenRegistrationHost extends AppCompatActivity {

    @BindView(R.id.fragment_container)LinearLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_registration_host);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        //NavigationController.showRegistrationFragment(getSupportFragmentManager());


        NavigationController.showLoginFragment(getSupportFragmentManager());
    }
}

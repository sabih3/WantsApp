package wantapp.netaq.com.wantapp.screens.login;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.utils.NavigationController;
import wantapp.netaq.com.wantapp.utils.UIUtils;


public class ScreenLogin extends Fragment implements LoginView{


    @BindView(R.id.field_phone)EditText phoneField;
    @BindView(R.id.btn_login)Button btnLogin;
    @BindView(R.id.login_parent)CoordinatorLayout loginParent;

    private ScreenLoginPresenter loginPresenter;
    public ScreenLogin() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.screen_login, container, false);

        ButterKnife.bind(this,rootView);
        loginPresenter = new ScreenLoginPresenter(this);
        btnLogin.setOnClickListener(new LoginButtonListener());
        return rootView;
    }

    @Override
    public void onPhoneNumberEmpty() {

        UIUtils.showSnackbar(loginParent,getResources().getString(R.string.msg_login_empty));
    }

    @Override
    public void onSignedIn() {
        NavigationController.showMainScreen(getContext());
    }

    @Override
    public void onSignInFailure() {

    }

    private class LoginButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            loginPresenter.SignIn(phoneField.getText().toString().trim());

        }
    }
}

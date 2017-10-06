package wantapp.netaq.com.wantapp.screens.register;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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

public class ScreenRegister extends Fragment implements RegisterView{

    @BindView(R.id.field_email)EditText fieldEmail;
    @BindView(R.id.field_phone)EditText fieldPhone;
    @BindView(R.id.btn_register)Button btnRegister;
    @BindView(R.id.register_parent)CoordinatorLayout registerParent;
    ScreenRegisterPresenter registerPresenter;

    public ScreenRegister() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.screen_register, container, false);

        ButterKnife.bind(this,root);
        registerPresenter = new ScreenRegisterPresenter(this);

        initViews();
        return root;
    }

    private void initViews() {

        btnRegister.setOnClickListener(new RegisterClickListener());
    }

    @Override
    public void onRegisterParamsEmpty() {
        UIUtils.hideProgress(registerParent);
        Snackbar.make(registerParent,"Both params are required",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOTPSent(int OTP) {
        UIUtils.hideProgress(registerParent);
        NavigationController.showVerificationScreen(getActivity().getSupportFragmentManager());

    }


    private class RegisterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            UIUtils.showProgress(registerParent);
            registerPresenter.doRegister(fieldEmail.getText().toString().trim(),fieldPhone.getText().toString().trim());
        }
    }
}

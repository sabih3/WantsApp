package wantapp.netaq.com.wantapp.screens.otp;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.utils.NavigationController;
import wantapp.netaq.com.wantapp.utils.UIUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenOTP extends Fragment implements OTPView{


    @BindView(R.id.field_otp)EditText fieldOTP;
    @BindView(R.id.resend_otp_parent)LinearLayout resendOtpParent;
    @BindView(R.id.btn_verify)Button btnVerify;
    @BindView(R.id.otp_parent)CoordinatorLayout otpParent;
    private View root;
    private OTPPresenter otpPresenter;

    public ScreenOTP() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.screen_otp, container, false);

        otpPresenter = new OTPPresenter(this);

        ButterKnife.bind(this,root);

        initViews();

        return root;
    }

    private void initViews() {

        btnVerify.setOnClickListener(new OTPVerifyListener());
    }

    @Override
    public void otpVerified() {
        NavigationController.showMainScreen(getContext());
    }

    @Override
    public void otpVerificationFailed() {
        UIUtils.showSnackbar(otpParent,"OTP is incorrect");
    }

    private class OTPVerifyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            otpPresenter.verifyOTP(fieldOTP.getText().toString().trim());
        }
    }
}

package wantapp.netaq.com.wantapp.screens.otp;

import wantapp.netaq.com.wantapp.utils.PreferencesManager;

/**
 * Created by sabih on 05-Oct-17.
 */

public class OTPPresenter {

    private OTPView OTPView;

    public OTPPresenter(OTPView otpView) {
        this.OTPView = otpView;
    }

    public void verifyOTP(String OTP) {

        int userGivenOTP = Integer.parseInt(OTP);

        if(userGivenOTP == PreferencesManager.getInstance().getOTP()){
            OTPView.otpVerified();
        }else{
            OTPView.otpVerificationFailed();
        }

    }
}

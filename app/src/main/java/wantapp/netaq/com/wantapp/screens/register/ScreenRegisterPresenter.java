package wantapp.netaq.com.wantapp.screens.register;

import android.content.Context;

import wantapp.netaq.com.wantapp.bals.OTPBAL;
import wantapp.netaq.com.wantapp.bals.responses.SMSListener;
import wantapp.netaq.com.wantapp.utils.PreferencesManager;
import wantapp.netaq.com.wantapp.utils.RandomInteger;

/**
 * Created by sabih on 20-Sep-17.
 */

public class ScreenRegisterPresenter {

    private Context mContext;
    private RegisterView registerView;

    public ScreenRegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void doRegister(String email, String mobileNumber) {

        if(email.isEmpty() || mobileNumber.isEmpty()){
            registerView.onRegisterParamsEmpty();
            return ;
        }else{
            final int OTP = RandomInteger.generateRandomNumber();
            OTPBAL.sendOTP(mobileNumber, OTP, new SMSListener() {
                @Override
                public void onSmsSent() {
                    PreferencesManager.getInstance().setOTP(OTP);
                    registerView.onOTPSent(OTP);
                }

                @Override
                public void onSmsSentFailure() {

                }

                @Override
                public void onRequestFailed() {

                }
            });

        }


    }
    
}

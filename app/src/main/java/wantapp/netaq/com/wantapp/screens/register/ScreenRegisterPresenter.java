package wantapp.netaq.com.wantapp.screens.register;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import wantapp.netaq.com.wantapp.utils.NavigationController;

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
            registerView.onOTPSent();
        }


    }

    public void showVerificationScreen() {
        registerView.onVerificationScreenDisplay();

    }
}

package wantapp.netaq.com.wantapp.screens.login;

import wantapp.netaq.com.wantapp.bals.AuthBAL;

/**
 * Created by sabih on 04-Oct-17.
 */

public class ScreenLoginPresenter {

    private LoginView loginView;

    public ScreenLoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public boolean checkIfFieldIsEmpty(String phoneNumber) {

        if(phoneNumber.isEmpty()){

            loginView.onPhoneNumberEmpty();
            return true;
        }else{
            performSignIn(phoneNumber);
        }
        return false;
    }

    private void performSignIn(String phoneNumber) {

        AuthBAL.signIn(phoneNumber);
    }
}

package wantapp.netaq.com.wantapp.bals;

import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.model.QBUser;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;
import wantapp.netaq.com.wantapp.screens.login.ScreenLoginPresenter;
import wantapp.netaq.com.wantapp.utils.QBUserHelper;

/**
 * Created by sabih on 04-Oct-17.
 */

public class AuthBAL {

    public static void signIn(String phoneNumber, final ScreenLoginPresenter.SignInListener signInListener) {
        // call sign in
        // if sign in is successfull
        //generate OTP and send to caller

        // in case of un successfull
        // return message

        QBUser qbUser = new QBUser(phoneNumber,phoneNumber);

        
        ChatSessionManager.getInstance().login(qbUser, new ChatSessionManager.QBLoginListener() {
            @Override
            public void onLoggedInChat() {
                signInListener.onSignedIn();
            }

            @Override
            public void onChatLoginFailure(QBResponseException e) {
                signInListener.onSignInFailure(e.toString());
            }
        });
    }
}

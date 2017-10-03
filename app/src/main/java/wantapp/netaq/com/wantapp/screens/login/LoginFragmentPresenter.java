package wantapp.netaq.com.wantapp.screens.login;

import android.widget.Toast;

import com.quickblox.chat.QBChatService;
import com.quickblox.core.exception.QBResponseException;

import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.chat.ChatSessionManager;
import wantapp.netaq.com.wantapp.utils.QBUserHelper;
/**
 * Created by sabih on 02-Oct-17.
 */

class LoginFragmentPresenter {

    private LoginView loginViewListener;

    public LoginFragmentPresenter(LoginView loginView) {
        this.loginViewListener = loginView;
    }

    public void loginQB(int tvID) {

        switch (tvID){
            case R.id.tv_sabih:
                loginViewListener.showProgress();
                if(QBChatService.getInstance().isLoggedIn()){
                   loginViewListener.onChatReady();
                }else{
                    ChatSessionManager.getInstance().login(QBUserHelper.sabih, new ChatSessionManager.QBLoginListener() {
                        @Override
                        public void onLoggedInChat() {
                            loginViewListener.onChatReady();
                        }

                        @Override
                        public void onChatLoginFailure(QBResponseException e) {
                            loginViewListener.onChatFailed();
                        }
                    });
                }

            break;

            case R.id.tv_azam:
                loginViewListener.showProgress();
                if(QBChatService.getInstance().isLoggedIn()){
                    loginViewListener.onChatReady();
                }else{
                    ChatSessionManager.getInstance().login(QBUserHelper.azam, new ChatSessionManager.QBLoginListener() {
                        @Override
                        public void onLoggedInChat() {
                            loginViewListener.onChatReady();
                        }

                        @Override
                        public void onChatLoginFailure(QBResponseException e) {
                            loginViewListener.onChatFailed();
                        }
                    });
                }

        }
    }
}

package wantapp.netaq.com.wantapp.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import wantapp.netaq.com.wantapp.screens.login.LoginView;
import wantapp.netaq.com.wantapp.screens.login.ScreenLoginPresenter;

import static org.mockito.Mockito.verify;

/**
 * Created by sabih on 03-Oct-17.
 */

public class ScreenLoginPresenterTest {

    @Test
    public void testIfPhoneNumberisEmpty(){
        LoginView  loginView = Mockito.mock(LoginView.class);
        ScreenLoginPresenter loginPresenter = new ScreenLoginPresenter(loginView);

        Assert.assertTrue(loginPresenter.checkIfFieldIsEmpty(""));
        verify(loginView).onPhoneNumberEmpty();
    }
}

package wantapp.netaq.com.wantapp.test;

import org.junit.Test;

import wantapp.netaq.com.wantapp.screens.register.RegisterView;
import wantapp.netaq.com.wantapp.screens.register.ScreenRegisterPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by sabih on 20-Sep-17.
 */
public class ScreenRegisterPresenterTest {



    @Test
    public void OTPSentTest(){
        RegisterView registerView = mock(RegisterView.class);
        ScreenRegisterPresenter registerPresenter = new ScreenRegisterPresenter(registerView);
        registerPresenter.doRegister("sabih.ahmed@netaq.ae","0544741150");
        //Assert.assertTrue(registerPresenter.doRegister("sabih.ahmed@netaq.ae","054741150"));
        //registerView.onOTPSent();
        verify(registerView).onOTPSent(OTP);
    }

    @Test
    public void paramsNotProvidedTest(){
        RegisterView registerView = mock(RegisterView.class);
        ScreenRegisterPresenter registerPresenter = new ScreenRegisterPresenter(registerView);
        registerPresenter.doRegister("sabih.ahmed@netaq.ae","");
        verify(registerView).onRegisterParamsEmpty();
    }

    @Test
    public void verificationScreenAppearTest(){
        RegisterView registerView = mock(RegisterView.class);
        ScreenRegisterPresenter registerPresenter  = new ScreenRegisterPresenter(registerView);
        registerPresenter.showVerificationScreen();
        

    }

}
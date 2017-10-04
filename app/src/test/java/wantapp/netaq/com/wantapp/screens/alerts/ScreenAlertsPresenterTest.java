package wantapp.netaq.com.wantapp.screens.alerts;

import org.junit.Test;
import org.mockito.Mockito;

import wantapp.netaq.com.wantapp.screens.login.LoginView;

import static org.junit.Assert.*;

/**
 * Created by sabih on 04-Oct-17.
 */
public class ScreenAlertsPresenterTest {


    AlertsView alertsView = Mockito.mock(AlertsView.class);
    ScreenAlertsPresenter alertsPresenter = new ScreenAlertsPresenter(alertsView);

    @Test
    public void setAlertsList(){
        alertsPresenter.setAlertsList();
    }



}
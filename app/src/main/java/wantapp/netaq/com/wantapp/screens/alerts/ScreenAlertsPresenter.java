package wantapp.netaq.com.wantapp.screens.alerts;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.bals.AlertsBAL;
import wantapp.netaq.com.wantapp.models.Alerts;

/**
 * Created by sabih on 04-Oct-17.
 */

public class ScreenAlertsPresenter {

    private AlertsView alertsView;

    public ScreenAlertsPresenter(AlertsView alertsView) {

        this.alertsView = alertsView;
    }


    public void setAlertsList() {
        ArrayList<Alerts> alerts = AlertsBAL.getAlerts();

        alertsView.onAlertsListReady(alerts);
    }
}

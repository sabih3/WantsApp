package wantapp.netaq.com.wantapp.screens.alerts;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.models.Alerts;

/**
 * Created by sabih on 04-Oct-17.
 */

public interface AlertsView {



    void onAlertsListReady(ArrayList<Alerts> alerts);
}

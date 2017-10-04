package wantapp.netaq.com.wantapp.bals;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.models.Alerts;

/**
 * Created by sabih on 04-Oct-17.
 */

public class AlertsBAL {

    public static ArrayList<Alerts> getAlerts() {

        return getDummyAlerts();

    }



    private static ArrayList<Alerts> getDummyAlerts(){
        ArrayList<Alerts> dummyAlertList = new ArrayList<>();
        Alerts alerts1 = new Alerts("5 people are looking for books");
        Alerts alerts2 = new Alerts("2 people are looking for Mobile Developers");
        Alerts alerts3 = new Alerts("4 people are looking for plumber");

        dummyAlertList.add(alerts1);
        dummyAlertList.add(alerts2);
        dummyAlertList.add(alerts3);

        return dummyAlertList;
    }
}

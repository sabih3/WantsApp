package wantapp.netaq.com.wantapp.utils;

import com.quickblox.users.model.QBUser;

/**
 * Created by sabih on 02-Oct-17.
 */

public class QBUserHelper {

    public static final QBUser azam = new QBUser("muhammad.azam@netaq.ae","netaq123");

    public static final QBUser refaat = new QBUser("muhammed.refaat@netaq.ae","netaq123");

    public static final QBUser sabih = new QBUser("sabih.ahmed@netaq.ae", "netaq123");


    public static void persistUser(QBUser user){

        PreferencesManager.getInstance().setUser(user);
    }
}

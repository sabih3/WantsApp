package wantapp.netaq.com.wantapp.db.managers;

import android.content.Context;

import wantapp.netaq.com.wantapp.db.helpers.DatabaseHelper;

/**
 * Created by sabih on 28-Sep-17.
 */

public class DBManager {

    private static DBManager instance;
    private DatabaseHelper databaseHelper;

    private DBManager dbManager;

    private DBManager(Context context) {
        databaseHelper = new DatabaseHelper (context);
    }


    public static void init(Context context) {
        if (null == instance) {
            instance = new DBManager(context);
        }
    }

    public static DBManager getInstance() {
        return instance;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}

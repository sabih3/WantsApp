package wantapp.netaq.com.wantapp.db.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.db.models.Message;

/**
 * Created by sabih on 28-Sep-17.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static String databaseName = "wantapp.db";
    private static int databaseVersion = 6;
    private Dao<Dialog,Integer> dialogDao;
    private Dao<Message,Integer> messageDao;

    private static final Class<?>[] TABLES = {
            Dialog.class,
            Message.class
    };

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TABLES[0]);
            TableUtils.createTable(connectionSource,TABLES[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
            if(newVersion > oldVersion){
                try {
                    TableUtils.dropTable(connectionSource,TABLES[0],false);
                    TableUtils.dropTable(connectionSource,TABLES[1],false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    TableUtils.createTable(connectionSource, TABLES[0]);
                    TableUtils.createTable(connectionSource,TABLES[1]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    public Dao<Dialog, Integer> getDialogDao() {
        if(dialogDao ==null){
            try {
                dialogDao = getDao(Dialog.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dialogDao;
    }

    public Dao<Message, Integer> getMessageDao(){
        if(messageDao == null){
            try {
                messageDao = getDao(Message.class);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return messageDao;
    }
}

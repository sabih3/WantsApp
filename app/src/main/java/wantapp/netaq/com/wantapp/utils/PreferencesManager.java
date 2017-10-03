package wantapp.netaq.com.wantapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.quickblox.users.model.QBUser;

/**
 * Created by sabih on 02-Oct-17.
 */

public class PreferencesManager {

    private static PreferencesManager instance;
    private static SharedPreferences prefs;

    private Context mContext;

    public void init(Context context){

        this.mContext = context;

        prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);

    }

    private PreferencesManager() {

    }

    public static PreferencesManager getInstance(){

        if(instance == null ){
            instance = new PreferencesManager();
        }
        return instance;

    }

    public void setLastSearchQuery(String searchQuery) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("searchQuery",searchQuery);
        editor.commit();
    }

    public String getLastSearchQuery(){
        String searchedQuery="";
        searchedQuery = prefs.getString("searchQuery","");
        return searchedQuery;

    }


    public void setUser(QBUser user) {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        editor.putString("QBuser",gson.toJson(user));
        editor.commit();
    }

    public QBUser getUser(){
        String userString = "";
        userString = prefs.getString("QBuser","");
        Gson gson = new Gson();
        QBUser qbUser = gson.fromJson(userString, QBUser.class);

        return qbUser;
    }


}

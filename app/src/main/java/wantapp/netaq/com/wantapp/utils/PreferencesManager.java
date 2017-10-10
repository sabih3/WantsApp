package wantapp.netaq.com.wantapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.quickblox.users.model.QBUser;

/**
 * Created by sabih on 02-Oct-17.
 */

public class PreferencesManager {

    private static final String KEY_OTP = "OTP";
    private static final String KEY_CHAT_ACTIVE = "KEY_CHAT_ACTIVE";
    private static final String KEY_ACTIVE_DIALOG_ID = "ACTIVE_DIALOG_ID";
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


    public void setOTP(int otp) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_OTP,otp);
        editor.commit();
    }

    public int getOTP() {
        int OTP = 0;
        OTP = prefs.getInt(KEY_OTP, 0);

        return OTP;
    }

    public void setChatIsActive(boolean isChatActive) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_CHAT_ACTIVE,isChatActive);
        editor.commit();
    }

    public boolean isChatActive(){
        return  prefs.getBoolean(KEY_CHAT_ACTIVE, false);
    }


    public void setActiveDialogID(String dialogId) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ACTIVE_DIALOG_ID,dialogId);
        editor.commit();
    }

    public String getActiveDialogID() {

        return prefs.getString(KEY_ACTIVE_DIALOG_ID,"");
    }
}

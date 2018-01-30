package app.kulture.kucherenko.init.com.kulture.settings;

import android.content.Context;
import android.content.SharedPreferences;

import app.kulture.kucherenko.init.com.kulture.ui.MApplication;

/**
 * Created by alex on 10/13/17.
 */
//Не желательно использовать как БД
//Только как класс для хранения мелких настроек приложения
public class MSharedPreferences {

    private static final String SHARED_PACKAGE = "user_info";
    private static final String IS_FIRST_TIME_LAUNCH = "launch";
    private static final String AUTH_KEY = "key";
    private static final String BRAINTREE_TOKEN = "braintree_token";
    private static final String USER_INFO = "json_user_info";
    private static final String APP_DATA = "json_app_data";

    private static MSharedPreferences loader;
    private SharedPreferences sharedPref;

    private MSharedPreferences() {
        sharedPref = MApplication.getInstance().getApplicationContext().getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE);
    }

    public static MSharedPreferences getInstance() {
        if (loader == null) loader = new MSharedPreferences();
        return loader;
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        sharedPref.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).apply();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public String getKey() {
        return sharedPref.getString(AUTH_KEY, null);
    }

    public void setKey(String key) {
        sharedPref.edit().putString(AUTH_KEY, key).apply();
    }

    public void setBraintreeToken(String token){
        sharedPref.edit().putString(BRAINTREE_TOKEN, token).apply();
    }

    public String getBraintreeToken() {
        return sharedPref.getString(BRAINTREE_TOKEN, null);
    }

    public void setUserInfo(String userInfo){
        sharedPref.edit().putString(USER_INFO, userInfo).apply();
    }

    public String getUserInfo() {
        return sharedPref.getString(USER_INFO, null);
    }


    public void setAppData(String teachers){
        sharedPref.edit().putString(APP_DATA, teachers).apply();
    }

    public String getAppData() {
        return sharedPref.getString(APP_DATA, null);
    }

}

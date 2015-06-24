package com.d1qkens.onixtestingapp.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {

    public static final String PREF_USERNAME = "username";

    static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void setUserName(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_USERNAME, username);
        editor.apply();
    }
    public static String getUserName(Context context) {
        return getSharedPreferences(context).getString(PREF_USERNAME,"");
    }
    public static void clearUserName(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }
}

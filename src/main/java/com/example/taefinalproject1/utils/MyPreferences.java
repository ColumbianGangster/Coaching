package com.example.taefinalproject1.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TAE_user2 on 22/01/2016.
 */
public class MyPreferences {
    private final static String APP_PREFERENCE_NAME = "com.example.taefinalproject1";

    public void savePreference(String key, String value, Context c){
        SharedPreferences prefs = c.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, value).apply();
    }
    public void savePreference(String key, int value, Context c){
        SharedPreferences prefs = c.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(key, value).apply();
    }
    public String retrievePreference(String key, Context c){
        SharedPreferences prefs = c.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, null); // return preference
    }
    public int retrieveIntPreference(String key, Context c){
        SharedPreferences prefs = c.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, -8000); // return preference
    }
}

package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.taefinalproject1.utils.NetworkAvailability;

import io.fabric.sdk.android.Fabric;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        NetworkAvailability.isConnectionAvailable(this);
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // The first time this Android App is run, launch the FirstTimeActivity. Else, launch the LoginActivity
            Intent firsttime = new Intent(this, FirstTimeActivity.class);
            startActivity(firsttime);

            // Record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
    }
}

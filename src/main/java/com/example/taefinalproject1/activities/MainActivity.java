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
        Intent intent;
        if (settings.getBoolean("my_first_time", true)) {
            Log.d("Comments", "First time");
            intent = new Intent(this, FirstTimeActivity.class);
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}

package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.services.MyService;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent mServiceIntent = new Intent(this, MyService.class);
        mServiceIntent.putExtra("mainaccount", getIntent().getStringExtra("mainaccount"));
        if(!getIntent().getStringArrayListExtra("smurfs").isEmpty()){ // if smurfs exist... i.e, if not empty
            mServiceIntent.putExtra("smurfs", getIntent().getStringArrayListExtra("smurfs"));
        }
    }
}

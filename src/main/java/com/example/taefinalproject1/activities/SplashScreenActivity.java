package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.ActionConstants;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.services.MyService;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    @Bind(R.id.splash_screen_image)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ButterKnife.bind(this);
        Log.i(Constants.TAG, "onCreate: SplashScreen: Loading image...");
        Picasso.with(this)
                .load(R.drawable.background1)
                .into(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mServiceIntent = new Intent(SplashScreenActivity.this, MyService.class);
                mServiceIntent.putExtra("mainaccount", getIntent().getStringExtra("mainaccount"));
                if (getIntent().getStringArrayListExtra("smurfs") != null) {
                    // this is null when RegisterActivity is not created
                    if (!getIntent().getStringArrayListExtra("smurfs").isEmpty()) { // if smurfs exist... i.e, if not empty
                        mServiceIntent.putExtra("smurfs", getIntent().getStringArrayListExtra("smurfs"));
                    }
                }
                mServiceIntent.setAction(ActionConstants.ACTION_SUMMONER_TO_ID);
                Log.i(Constants.TAG, "run: " + mServiceIntent.getStringExtra("mainaccount"));
                startService(mServiceIntent);

                Intent intent = new Intent(SplashScreenActivity.this, MyService.class);
                intent.setAction(ActionConstants.CHAMPION_TO_ID);
                startService(intent);
                Intent activityintent = new Intent(SplashScreenActivity.this, ParentActivity.class);
                activityintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(activityintent);
                finish();
            }
        }, 3000);
        imageView = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

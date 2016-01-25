package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.ActionConstants;
import com.example.taefinalproject1.services.MyService;

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

//        Picasso.with(this)
//                .load(R.drawable.zed_background)
//                .placeholder(R.drawable.place_holder)
//                .error(R.drawable.error)
//                .into(imageView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent mServiceIntent = new Intent(this, MyService.class);
        mServiceIntent.putExtra("mainaccount", getIntent().getStringExtra("mainaccount"));
        if(!getIntent().getStringArrayListExtra("smurfs").isEmpty()){ // if smurfs exist... i.e, if not empty
            mServiceIntent.putExtra("smurfs", getIntent().getStringArrayListExtra("smurfs"));
        }
        mServiceIntent.setAction(ActionConstants.ACTION_SUMMONER_TO_ID);
        startService(mServiceIntent);

//        Intent intent = new Intent(this, MyService.class);
//        intent.setAction(ActionConstants.CHAMPION_TO_ID);
//        startService(intent);
    }
}

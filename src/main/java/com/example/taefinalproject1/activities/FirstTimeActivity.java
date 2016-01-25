package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.taefinalproject1.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class FirstTimeActivity extends AppCompatActivity {
    @Bind(R.id.first_time_button)
    Button button;
    @OnClick(R.id.first_time_button) void click(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_activity);
    }
}

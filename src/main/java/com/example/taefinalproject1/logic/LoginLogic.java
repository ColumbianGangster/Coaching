package com.example.taefinalproject1.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class LoginLogic {
    Context context;
    View view;
    final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;

    public LoginLogic(Context context, View view){
        this.context = context;
        this.view = view;
        settings = this.context.getSharedPreferences(PREFS_NAME, 0);
    }

    public Boolean do_you_remember_me(){
        return settings.getBoolean("remember_me", false);
    }

    public void remember_me(Boolean bool){
        settings.edit().putBoolean("remember_me", bool).commit();
    }

    public void rememberName(){
        EditText username = (EditText) view.findViewById(R.id.login_username);
        Log.i(Constants.TAG, "rememberName: "+username.getText().toString());
        settings.edit().putString("username", username.getText().toString()).commit();
    }
    public void rememberPassword(){
        EditText password = (EditText) view.findViewById(R.id.login_password);
        Log.i(Constants.TAG, "rememberPassword: "+password.getText().toString());
        settings.edit().putString("password", password.getText().toString()).commit();
    }
    public String getName(){
        Log.i(Constants.TAG, "getName: "+settings.getString("username",""));
        return settings.getString("username","");
    }
    public String getPassword(){
        Log.i(Constants.TAG, "getPassword: "+settings.getString("password",""));
        return settings.getString("password", "");
    }
}

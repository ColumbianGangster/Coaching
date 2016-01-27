package com.example.taefinalproject1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.logic.LoginLogic;
import com.example.taefinalproject1.persistence.MyDatabaseManager;
import com.example.taefinalproject1.utils.MyPreferences;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MaxLength;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class LoginActivity extends AppCompatActivity {
    LoginLogic loginLogic;
    ProgressDialog progressDialog;
    //https://github.com/inmite/android-validation-komensky
    @NotEmpty(messageId = R.string.validation_name, order = 1)
    @MinLength(value = 3, messageId = R.string.validation_name_length_toosmall, order = 2)
    @MaxLength(value = 16, messageId = R.string.validation_name_length_toobig, order = 3)
    @RegExp(value = "^[a-zA-Z0-9]+$", messageId = R.string.validation_name_only_alphanumeric, order = 4)
    @Bind(R.id.login_username)
    EditText username;
    @NotEmpty(messageId = R.string.validation_password)
    @Bind(R.id.login_password)
    EditText password;

    @Bind(R.id.login_remember_account)
    CheckBox rememberme;
    // TODO: 21/01/2016 Login automatically implementation 
    @Bind(R.id.login_forgot_your_password)
    Button forgot_password;
    @Bind(R.id.login_with_gmail)
    Button login_with_gmail;
    @Bind(R.id.login_create_account)
    Button create_account;

    @OnClick(R.id.login_button) void login(){
        if(!username.getText().toString().isEmpty()){
            if(!password.getText().toString().isEmpty()){
                attemptLogin();
            } else {
                Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            }
        } else { // if empty
            Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.login_create_account) void createAccount(){
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.login_forgot_your_password) void forgotPassword(){
        // TODO: 20/01/2016 Implement forgot password button
        Toast.makeText(this, "Forgot your password", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.login_with_gmail) void loginWithGmail(){
        // TODO: 20/01/2016 Implement login with gmail button
    }
    @OnCheckedChanged(R.id.login_remember_account) void onChecked(boolean checked) {
        if(checked){
            loginLogic.remember_me(true);
            Log.i(Constants.TAG, "onChecked: " + loginLogic.do_you_remember_me());
        } else {
            loginLogic.remember_me(false);
            Log.i(Constants.TAG, "onChecked: " + loginLogic.do_you_remember_me());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginLogic = new LoginLogic(this,findViewById(R.id.login_linear));
        ButterKnife.bind(this);
        Log.i(Constants.TAG, "onCreate: do I remember you?: " + loginLogic.do_you_remember_me());
        if(loginLogic.do_you_remember_me()){
            // prevents annoying default values on SharedPreference
            username.setText(loginLogic.getName());
            password.setText(loginLogic.getPassword());
            rememberme.setChecked(true);
        }
        FormValidator.validate(this, new SimpleErrorPopupCallback(this));
    }
    private void attemptLogin(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Authenticating");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        if(authenticating(username.getText().toString(), password.getText().toString())){
           dismiss();
            Log.i(Constants.TAG, "attemptLogin: successful");
           authenticated();
        } else {
            dismiss();
            Log.i(Constants.TAG, "attemptLogin: failed");
            Toast.makeText(this, "Failed to authenticate", Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean authenticating(String username, String password){
        MyDatabaseManager myDatabaseManager = new MyDatabaseManager(this);
        return myDatabaseManager.authenticate(username, password);
    }
    private void authenticated(){
        if(loginLogic.do_you_remember_me()){
            loginLogic.rememberName();
            loginLogic.rememberPassword();
        }
        ArrayList<String> smurfAccounts = new ArrayList<>();
        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.putExtra("mainaccount", username.getText().toString());
        intent.putExtra("smurfs", smurfAccounts);
        MyPreferences myPreferences = new MyPreferences();
        myPreferences.savePreference("mainaccount", username.getText().toString(), this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void dismiss()
    {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}

package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.example.taefinalproject1.R;
import com.example.taefinalproject1.utils.NetworkAvailability;

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
import io.fabric.sdk.android.Fabric;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class LoginActivity extends AppCompatActivity {
    //https://github.com/inmite/android-validation-komensky
    @NotEmpty(messageId = R.string.validation_name)
    @MinLength(value = 3, messageId = R.string.validation_name_length_toosmall, order = 2)
    @MaxLength(value = 16, messageId = R.string.validation_name_length_toobig, order = 3)
    @RegExp(value = "^[a-zA-Z0-9]+$", messageId = R.string.validation_name_only_alphanumeric, order = 4)
    // TODO: 20/01/2016 Fix the regular expressions so that it does not match more than 2 consecutive spaces
    // "^[ ]{2,}$"
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

    @OnClick(R.id.login_create_account) void createAccount(){
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.login_forgot_your_password) void forgotPassword(){
        // TODO: 20/01/2016 Implement forgot password button
    }
    @OnClick(R.id.login_with_gmail) void loginWithGmail(){
        // TODO: 20/01/2016 Implement login with gmail button 
    }
    @OnCheckedChanged(R.id.login_remember_account) void onChecked(boolean checked) {
        if(checked){
            // TODO: 20/01/2016 Implement remember me logic
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        ButterKnife.bind(this);
        FormValidator.validate(this, new SimpleErrorPopupCallback(this));
    }
}

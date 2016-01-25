package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.utils.MyPreferences;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class RegisterActivity extends AppCompatActivity {
//    @Bind(R.id.register_add_edit_text)
//    Button add_edit_text;
    @Bind(R.id.register_mainaccount_edittext)
    EditText mainAccount;
    ArrayList<EditText> smurfs = new ArrayList<EditText>();

    @OnClick(R.id.register_add_edit_text) void add(){
        LinearLayout li = (LinearLayout) findViewById(R.id.register_edit_text_container);
        EditText et=new EditText(this);
        et.setHint("Smurf account");
        smurfs.add(et);
        li.addView(et);
    }
    @OnClick(R.id.register_accept_button) void register(){
        // TODO: 21/01/2016 Add registration functionality to the button
        ArrayList<String> smurfAccounts = new ArrayList<>();
        for(EditText e : smurfs){
            smurfAccounts.add(e.getText().toString());
        }

        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mainaccount", mainAccount.getText().toString());
        intent.putExtra("smurfs", smurfAccounts);
        MyPreferences myPreferences = new MyPreferences();
        myPreferences.savePreference("mainaccount", mainAccount.getText().toString(), this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);
    }
}

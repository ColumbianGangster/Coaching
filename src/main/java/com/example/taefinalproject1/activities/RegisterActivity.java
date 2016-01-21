package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.taefinalproject1.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class RegisterActivity extends AppCompatActivity {
//    @Bind(R.id.register_add_edit_text)
//    Button add_edit_text;

    @OnClick(R.id.register_add_edit_text) void add(){
        LinearLayout li = (LinearLayout) findViewById(R.id.register_edit_text_container);
        EditText et=new EditText(this);
        et.setText("Smurf account");
        li.addView(et);

    }
    @OnClick(R.id.register_accept_button) void register(){
        // TODO: 21/01/2016 Add registration functionality to the button
        Intent intent = new Intent(this, ParentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

package com.example.taefinalproject1.logic;

import android.content.Context;
import android.content.Intent;

import com.example.taefinalproject1.constants.EmailConstants;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class FeedbackLogic {

    public FeedbackLogic(Context context){
        startMailActivity(context);
    }

    public void startMailActivity(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(EmailConstants.MESSAGE_TYPE);
        intent.putExtra(Intent.EXTRA_EMAIL, EmailConstants.DEVELOPER_EMAIL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, EmailConstants.WELCOME_FEEDBACK_MSG ));
    }
}

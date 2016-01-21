package com.example.taefinalproject1.services;

import android.content.Context;
import android.content.Intent;

import com.example.taefinalproject1.adapters.DataRestAdapter;
import com.example.taefinalproject1.constants.ActionConstants;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class IntentHandler {
    public IntentHandler(Intent intent, Context context){
        DataRestAdapter dataRestAdapter = new DataRestAdapter(context);
        switch (intent.getAction()){
            case ActionConstants.ACTION_SUMMONER_TO_ID:
                dataRestAdapter.getIdBySummonerName(intent.getDataString(), intent.getDataString());
        }
    }
}

package com.example.taefinalproject1.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.taefinalproject1.adapters.DataRestAdapter;
import com.example.taefinalproject1.constants.ActionConstants;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.constants.RestConstants;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class IntentHandler {
    public IntentHandler(Intent intent, Context context){
        switch (intent.getAction()){
            case ActionConstants.ACTION_SUMMONER_TO_ID:
                DataRestAdapter dataRestAdapter = new DataRestAdapter(context);
                Log.i(Constants.TAG, "IntentHandler: " + intent.getStringExtra("mainaccount"));
                dataRestAdapter.getIdBySummonerName("euw", intent.getStringExtra("mainaccount"), RestConstants.API_KEY);
            case ActionConstants.CHAMPION_TO_ID:
                Log.i(Constants.TAG, "IntentHandler: Champion to Id");
                DataRestAdapter global = new DataRestAdapter(context,true);
                global.getChampionToIdMappings("euw", RestConstants.API_KEY);
        }
    }
}

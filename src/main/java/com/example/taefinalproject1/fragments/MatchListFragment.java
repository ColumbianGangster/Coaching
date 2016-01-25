package com.example.taefinalproject1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.taefinalproject1.apis.IRest;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.logic.EpochConversion;
import com.example.taefinalproject1.models.summonermatchesbyseason.MatchlistBySummoner;
import com.example.taefinalproject1.utils.RetrofitErrorHandler;

import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class MatchListFragment extends Fragment {
    private IRest irest;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void getMatchList(String region, String id, String rankedQueues, String seasons, String api_key){
        irest.getMatchList(region, id, rankedQueues, seasons, api_key, new Callback<MatchlistBySummoner>() {
            @Override
            public void success(MatchlistBySummoner matchlistBySummoner, Response response) {
                matchlistBySummoner.getMatches().get(0).getMatchId();

                EpochConversion epochConversion = new EpochConversion();
                Date d = epochConversion.convert(matchlistBySummoner.getMatches().get(0).getTimestamp());

                matchlistBySummoner.getMatches().get(0).getChampion();
                matchlistBySummoner.getMatches().get(0).getQueue();
                matchlistBySummoner.getMatches().get(0).getSeason();
                matchlistBySummoner.getMatches().get(0).getRegion();
                matchlistBySummoner.getMatches().get(0).getRole();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(Constants.TAG, "failure: " + error);
                RetrofitErrorHandler reh = new RetrofitErrorHandler(getActivity(), error);
            }
        });
    }
}

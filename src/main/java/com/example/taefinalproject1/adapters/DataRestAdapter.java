package com.example.taefinalproject1.adapters;

import android.content.Context;

import com.example.taefinalproject1.apis.IRest;
import com.example.taefinalproject1.constants.RestConstants;
import com.example.taefinalproject1.models.nametoidmap.NameToIdMap;
import com.example.taefinalproject1.utils.RetrofitErrorHandler;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class DataRestAdapter{
    private IRest irest;
    private Context context;

    public DataRestAdapter(Context context){
        this.context=context;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(RestConstants.BASE_URL)
                .build();
        irest = restAdapter.create(IRest.class);
    }

    public NameToIdMap getIdBySummonerName(String region, String summonerName){
        final NameToIdMap[] myNameToIdMap = new NameToIdMap[1];
        irest.getIdBySummonerName(region, summonerName, new Callback<NameToIdMap>() {
            @Override
            public void success(NameToIdMap nameToIdMap, Response response) {
                myNameToIdMap[0] = nameToIdMap;
            }

            @Override
            public void failure(RetrofitError error) {
                RetrofitErrorHandler reh = new RetrofitErrorHandler(context, error);
            }
        });
        return myNameToIdMap[0];
    }
}

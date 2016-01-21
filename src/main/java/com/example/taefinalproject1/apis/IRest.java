package com.example.taefinalproject1.apis;

import com.example.taefinalproject1.models.nametoidmap.NameToIdMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by TAE_user2 on 18/01/2016.
 */
public interface IRest {
    // TODO: 18/01/2016 Implement all of the rest interface functions
    @GET("{region}/v1.4/summoner/by-name/{summonerNames}")
    public void getIdBySummonerName(@Query("region") String region, @Query("summonerName") String summonerName, Callback<NameToIdMap> response);

//    Call<List<User>> groupList(@Path("id") int groupId);
}

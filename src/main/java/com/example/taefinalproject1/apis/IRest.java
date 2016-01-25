package com.example.taefinalproject1.apis;

import com.example.taefinalproject1.models.championtoidmappings.Championtoidmapping;
import com.example.taefinalproject1.models.nametoidmap.NameToIdMap;
import com.example.taefinalproject1.models.summonermatchesbyseason.MatchlistBySummoner;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by TAE_user2 on 18/01/2016.
 */
public interface IRest {
    // TODO: 18/01/2016 Implement all of the rest interface functions
    @GET("/{region}/v1.4/summoner/by-name/{summonerNames}")
    public void getIdBySummonerName(@Path("region") String region, @Path("summonerNames") String summonerName, @Query("api_key") String api_key, Callback<NameToIdMap> response);

    // Call<List<User>> groupList(@Path("id") int groupId);

    // https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion?api_key=cd659191-9c3a-4dd0-b352-b387a142bfc9

    @GET("/static-data/{region}/v1.2/champion")
    public void getChampionIds(@Path("region") String region, @Query("api_key") String api_key, Callback<Championtoidmapping> response);

//    @GET("/euw/v2.2/matchlist/by-summoner/42829492?rankedQueues=RANKED_SOLO_5x5&seasons=SEASON2016&api_key=cd659191-9c3a-4dd0-b352-b387a142bfc9")
    @GET("/{region}/v2.2/matchlist/by-summoner/{summonerId}")
    public void getMatchList(@Path("region") String region, @Path("summonerId") String id, @Query("rankedQueues") String rankedQueues, @Query("seasons") String seasons,@Query("api_key") String api_key, Callback<MatchlistBySummoner> response);
}

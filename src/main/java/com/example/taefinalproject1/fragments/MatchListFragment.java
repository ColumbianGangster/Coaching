package com.example.taefinalproject1.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.adapters.DataRestAdapter;
import com.example.taefinalproject1.adapters.MatchListAdapter;
import com.example.taefinalproject1.apis.IRest;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.constants.RestConstants;
import com.example.taefinalproject1.logic.EpochConversion;
import com.example.taefinalproject1.models.custom.MatchListData;
import com.example.taefinalproject1.models.summonermatchesbyseason.Match;
import com.example.taefinalproject1.models.summonermatchesbyseason.MatchlistBySummoner;
import com.example.taefinalproject1.utils.MyPreferences;
import com.example.taefinalproject1.utils.RetrofitErrorHandler;

import java.util.ArrayList;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class MatchListFragment extends Fragment {
    private IRest irest;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private ArrayList<MatchListData> dataset = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.matchlist_fragment_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.matchlist_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        MyPreferences myPreference = new MyPreferences();
        getMatchList("euw", Integer.toString(myPreference.retrieveIntPreference(myPreference.retrievePreference("mainaccount", getContext()), getContext())), "RANKED_SOLO_5x5", "SEASON2016", RestConstants.API_KEY, v);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Getting data...");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        // TODO: 25/01/2016 replace with Enum or Constants
        return v;
    }

    public void getMatchList(String region, String id, String rankedQueues, String seasons, String api_key, final View v){
        DataRestAdapter dataRestAdapter = new DataRestAdapter(getContext());
        irest = dataRestAdapter.makeRestAdapter(RestConstants.BASE_URL);
        irest.getMatchList(region, id, rankedQueues, seasons, api_key, new Callback<MatchlistBySummoner>() {
            @Override
            public void success(MatchlistBySummoner matchlistBySummoner, Response response) {
                dismiss();
                EpochConversion epochConversion = new EpochConversion();

                for (Match match : matchlistBySummoner.getMatches()) {
                    Log.i(Constants.TAG, "success: getMatchList: ID: " + match.getMatchId());
                    Date date = epochConversion.convert(match.getTimestamp());

                    Integer i = match.getChampion();
                    MyPreferences myPreferences = new MyPreferences();
                    String championName = myPreferences.retrievePreference(Integer.toString(i), getActivity());

                    String rankedQueue = match.getQueue();
                    String lane = match.getLane();
                    String season = match.getSeason();
                    String region = match.getRegion();
                    String role = match.getRole();
                    Long matchid = match.getMatchId();

                    MatchListData matchListData = new MatchListData(championName, lane, rankedQueue, season, region, role, matchid, date);
                    dataset.add(matchListData);
                }

                adapter = new MatchListAdapter(dataset, getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                dismiss();
                Log.i(Constants.TAG, "failure: " + error);
                RetrofitErrorHandler reh = new RetrofitErrorHandler(getActivity(), error);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        dismiss();
    }

    public void dismiss()
    {
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}

package com.example.taefinalproject1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.models.custom.MatchListData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class PlayerEffortFragment extends Fragment {
    BarChart chart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.player_effort_fragment, container, false);

        ArrayList<MatchListData> matchListDataArrayList = getArguments().getParcelableArrayList("match_list");
        int n = 11;
        ArrayList<Integer> frequencyArrayList = getGamesPerDay(matchListDataArrayList);
        chart = new BarChart(getActivity());

        BarData data = new BarData(labels(n), r(frequencyArrayList));
        Log.i(Constants.TAG, "onCreateView: " + data.getDataSets().get(0).toString());

        chart.setData(data);
        Log.i(Constants.TAG, "onCreateView: chart...:" + chart.getBarData().toString());
        chart.setDescription("# of times Player played per day");
        chart.animateY(5000);
        chart.setDragEnabled(true);
        chart.setDoubleTapToZoomEnabled(true);
        FrameLayout parent = (FrameLayout) v.findViewById(R.id.parent_layout);
        parent.addView(chart);
        return v;
    }

    private ArrayList<Integer> getGamesPerDay(ArrayList<MatchListData> input){
        // zero initialises an Array List
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        ArrayList<Date> dates = getDates(input);
        SparseArray<Integer> frequency = new SparseArray<>();
        for(Date date : dates){ // loop over list of dates
            Log.i(Constants.TAG, "getGamesPerDay: to string" + date.toString());
            String[] date_array = getDateArray(date.toString());
            int day = Integer.parseInt(date_array[2]);
            Log.i(Constants.TAG, "getGamesPerDay: DAY "+day);
            Integer prev = frequency.get(day, 0);
            Log.i(Constants.TAG, "getGamesPerDay: PREV" + prev);
            frequency.append(day, prev + 1);
        }
        Log.i(Constants.TAG, "getGamesPerDay: FREQUENCY SIZEOF" + frequency.size());
        for(int j = 0; j < frequency.size(); ++j){
            int key = frequency.keyAt(j);
            Integer in = frequency.get(key);
            Log.i(Constants.TAG, "getGamesPerDay: "+in);
            frequencies.add(in);
        }
        Log.i(Constants.TAG, "getGamesPerDay: SIZEOF" + frequencies.size());
        return frequencies;
    }
    private ArrayList<Date> getDates(ArrayList<MatchListData> matchListDataArrayList){
        ArrayList<Date> dates = new ArrayList<>();
        for(MatchListData matchListData : matchListDataArrayList){
            dates.add(matchListData.getDate());
        }
        return dates;
    }

    private String[] getDateArray(String date){
        StringTokenizer st = new StringTokenizer(date);
        String[] date_array = new String[st.countTokens()];
        int i = 0;
//            date_array[0] = Wed
//            date_array[1] = Jan
//            date_array[2] = 20
//            date_array[3] = 11:01:15
//            date_array[4] = EST
//            date_array[5] = 2016
        while (st.hasMoreTokens()) {
            date_array[i] = st.nextToken();
            Log.i(Constants.TAG, "getGamesPerDay: "+date_array[i]);
            i++;
        }
        return date_array;
    }

    private BarDataSet r(ArrayList<Integer> al){
        ArrayList<BarEntry> entries = new ArrayList<>();
        int i = 0;
        for(Integer e : al){
            entries.add(new BarEntry(e.floatValue(), i));
            i++;
        }
        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        return dataset;
    }

    private ArrayList<String> labels(int n){
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            labels.add("Day "+i);
        }
        return labels;
    }
}

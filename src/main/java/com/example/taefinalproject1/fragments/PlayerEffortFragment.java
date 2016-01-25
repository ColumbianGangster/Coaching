package com.example.taefinalproject1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class PlayerEffortFragment extends Fragment {
    BarChart chart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.player_effort_fragment, container, false);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(4); al.add(8); al.add(6); al.add(12); al.add(18); al.add(9);
        al.add(2); al.add(6); al.add(13); al.add(1); al.add(20); al.add(10);

        chart = new BarChart(getActivity());

        //getActivity().getIntent().getIntegerArrayListExtra("dataset");
        BarData data = new BarData(labels(), r(al));
//        LimitLine line = new LimitLine(10f);
//        data.addLimitLine(line);
        Log.i(Constants.TAG, "onCreateView: " + data.getDataSets().get(0).toString());

        chart.setData(data);
        Log.i(Constants.TAG, "onCreateView: chart...:" + chart.getBarData().toString());
        chart.setDescription("# of times Player played per day");
        chart.animateY(5000);
        chart.setDragEnabled(true);
        chart.setDoubleTapToZoomEnabled(true);
        FrameLayout parent = (FrameLayout) v.findViewById(R.id.parent_layout);
        parent.addView(chart);
//        ButterKnife.bind(this,v);
        return v;
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

    private ArrayList<String> labels(){
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            labels.add("Day "+i);
        }
        return labels;
    }
}

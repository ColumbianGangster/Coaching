package com.example.taefinalproject1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.activities.ParentActivity;
import com.example.taefinalproject1.adapters.GalleryAdapter;
import com.example.taefinalproject1.constants.Constants;

/**
 * Created by TAE_user2 on 15/01/2016.
 */
public class GalleryFragment extends Fragment {
    GalleryAdapter galleryAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.gallery_fragment_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridView gridview = (GridView) getActivity().findViewById(R.id.gallery_gridview);
        galleryAdapter = new GalleryAdapter(getActivity());
        gridview.setAdapter(galleryAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                makeWebView(galleryAdapter.getName(position));
                Toast.makeText(getActivity(), "Loading...",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void makeWebView(String champion_name){
        ParentActivity parentActivity = (ParentActivity) getContext();
        parentActivity.grid_item_web_view(makeUrl(champion_name));
    }
    public String makeUrl(String champion_name){
        Log.e(Constants.TAG, "makeUrl: "+champion_name);
        String base_url = "http://leagueoflegends.wikia.com/wiki/";
        if(champion_name.contains("Mundo")){
            return base_url+"Mundo";
        } else if (champion_name.contains("khazix")){
            return base_url+"Kha%27Zix";
        } else if (champion_name.contains("leesin")){
            return base_url+"lee_sin";
        } else if (champion_name.contains("jarvaniv")){
            return base_url+"Jarvan_IV";
        } else if (champion_name.contains("masteryi")){
            return base_url+"Master_Yi";
        } else if (champion_name.contains("tahmkench")){
            return base_url+"Tahm_Kench";
        } else if (champion_name.contains("xinzhao")){
            return base_url+"Xin_Zhao";
        } else if (champion_name.contains("monkeyking")){
            return base_url+"Wukong";
        }
        return base_url+champion_name;
    }
}

package com.example.taefinalproject1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.models.custom.MatchListData;
import com.example.taefinalproject1.utils.MyPreferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder>  {
    ArrayList<MatchListData> dataset;
    Context context;

    @Override
    public MatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matchlist_fragment_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
//                .load(Uri.parse(dataset.get(position).getChampionName().toLowerCase()))
                .load(getUrl(dataset.get(position).getChampionName()))
                .noFade()
                .resize(100,100)
                .centerCrop()
                .into(holder.matchlist_circleImageView);
        holder.matchlist_champion.setText(dataset.get(position).getChampionName());
        holder.matchlist_lane.setText(dataset.get(position).getLane());
        MyPreferences myPreferences = new MyPreferences();

        holder.matchlist_playername.setText(myPreferences.retrievePreference("mainaccount", context));
        holder.matchlist_queue.setText(dataset.get(position).getRankedQueue());
        holder.matchlist_season.setText(dataset.get(position).getSeason());
        holder.matchlist_timestamp.setText(dataset.get(position).getDate().toString());
    }
    private String getUrl(String fromJSON){
        if(fromJSON.equals("LeBlanc") || fromJSON.equals("Leblanc")) {
            return "http://ddragon.leagueoflegends.com/cdn/5.24.2/img/champion/Leblanc.png";
        } else {
            return "http://ddragon.leagueoflegends.com/cdn/5.24.2/img/champion/" + fromJSON + ".png";
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @Bind(R.id.matchlist_champion_image)
        CircleImageView matchlist_circleImageView;
        @Bind(R.id.matchlist_champion)
        TextView matchlist_champion;
        @Bind(R.id.matchlist_timestamp)
        TextView matchlist_timestamp;
        @Bind(R.id.matchlist_playername)
        TextView matchlist_playername;
        @Bind(R.id.matchlist_lane)
        TextView matchlist_lane;
        @Bind(R.id.matchlist_queue)
        TextView matchlist_queue;
        @Bind(R.id.matchlist_season)
        TextView matchlist_season;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
    public MatchListAdapter(ArrayList<MatchListData> matchListDataArrayList, Context context){
        this.dataset = matchListDataArrayList;
        this.context = context;
    }
}

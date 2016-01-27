package com.example.taefinalproject1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.activities.ParentActivity;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.listeners.ItemClickListener;
import com.example.taefinalproject1.utils.MyPreferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TAE_user2 on 26/01/2016.
 */
public class TeamBuilderAdapter extends RecyclerView.Adapter<TeamBuilderAdapter.ViewHolder> {
    Context context;
    public ViewHolder viewholder;

    ArrayList<String> dataset = new ArrayList<String>();

    public TeamBuilderAdapter(ArrayList<String> mydataset, Context context){
        this.dataset = mydataset;
        this.context = context;
    }

    public void add(String s){
        dataset.add(s);
        Log.i(Constants.TAG, "add: " + s);
    }

    public void del(String s){
        dataset.remove(s);
    }

    public ArrayList<String> get(){
        return dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CircleImageView champion;
        public CircleImageView champion2;
        public CircleImageView champion3;
        public CircleImageView champion4;
        public TextView player_name;
        private ItemClickListener itemClickListener;

        public ViewHolder(View v) {
            super(v);
            champion = (CircleImageView) v.findViewById(R.id.team_builder_champion_image);
            player_name = (TextView) v.findViewById(R.id.team_builder_playername);
            champion2 = (CircleImageView) v.findViewById(R.id.team_builder_champion_image2);
            champion3 = (CircleImageView) v.findViewById(R.id.team_builder_champion_image3);
            champion4 = (CircleImageView) v.findViewById(R.id.team_builder_champion_image4);

            v.setOnClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getPosition(), false);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teambuilder_fragment_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.viewholder = holder;
        holder.player_name.setText(dataset.get(position));
        Picasso.with(context)
                .load(R.drawable.aatrox)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
//                .resize(100, 100).centerCrop()
                .into(holder.champion);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                MyPreferences myPreference = new MyPreferences();
                myPreference.savePreference("mainaccount", dataset.get(position), context);
                Log.i(Constants.TAG, "onClick: within TeamBuilderAdapter registered");
                ParentActivity parentActivity = (ParentActivity) context;
                parentActivity.match_list();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}

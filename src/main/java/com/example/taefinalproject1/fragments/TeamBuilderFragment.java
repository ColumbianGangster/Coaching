package com.example.taefinalproject1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.adapters.TeamBuilderAdapter;
import com.example.taefinalproject1.constants.ActionConstants;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.services.MyService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class TeamBuilderFragment extends android.support.v4.app.Fragment {
    private Toolbar toolbar;
    private ImageButton add_button;
    private ImageButton del_button;
    private ImageButton view_button;
    private ImageButton save_team_button;
    private ImageButton add_champion_button;
    private ImageButton remove_champion_button;

    private String prev_toolbar_title;
    private EditText editText;
    private int datasetsize = 0;

    private ArrayList<String> dataset = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();


    private RecyclerView recyclerView;
    private TeamBuilderAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // TODO: 20/01/2016 Build the team builder fragment, as well as the XML

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.teambuilder_fragment_list, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.hideOverflowMenu();

        editText = (EditText) v.findViewById(R.id.team_builder_edit);
        adapter = new TeamBuilderAdapter(dataset, getActivity());
        add_button = new ImageButton(getActivity());
        del_button = new ImageButton(getActivity());
        view_button = new ImageButton(getActivity());
        save_team_button = new ImageButton(getActivity());
        add_champion_button = new ImageButton(getActivity());
        remove_champion_button = new ImageButton(getActivity());

        add_button.setBackgroundResource(R.drawable.ic_action_add_player);
        del_button.setBackgroundResource(R.drawable.ic_action_delete_player);

        del_button.setPadding(0,0,5,0);

        add_champion_button.setBackgroundResource(R.drawable.ic_action_add_character);
        remove_champion_button.setBackgroundResource(R.drawable.ic_action_delete_player);

        remove_champion_button.setPadding(0,0,5,0);

        save_team_button.setBackgroundResource(R.drawable.ic_action_save_team);

//        del_button.setText("Del");
//        view_button.setText("View");
//        add_champion_button.setText("Add Champion");
//        remove_champion_button.setText("Del Champion");
//        save_team_button.setText("Save Team");

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constants.TAG, "onClick: Add");
                if(!editText.getText().toString().isEmpty()){
                    if(!players.contains(editText.getText().toString())){
                        adapter.add(editText.getText().toString());
                        adapter.notifyItemInserted(datasetsize);
                        editText.setText("");
                        Intent mServiceIntent = new Intent(getActivity(), MyService.class);
                        mServiceIntent.putExtra("mainaccount", adapter.get().get(datasetsize));
                        mServiceIntent.setAction(ActionConstants.ACTION_SUMMONER_TO_ID);
                        Log.i(Constants.TAG, "run: " + mServiceIntent.getStringExtra("mainaccount"));
                        getActivity().startService(mServiceIntent);
                        datasetsize++;
                    } else {
                        Toast.makeText(getContext(), "Player already exists on this team", Toast.LENGTH_SHORT);
                    }
                } else {
                    Toast.makeText(getContext(), "Please enter a player name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constants.TAG, "onClick: Del");
                if(datasetsize > 0){
                    int index = adapter.get().indexOf(editText.getText().toString());
                    adapter.del(editText.getText().toString());
                    adapter.notifyItemRemoved(index);
                    editText.setText("");
                    datasetsize--;
                }
            }
        });

        add_champion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datasetsize > 0){
                    int index = adapter.get().indexOf(editText.getText().toString());
                    Log.i(Constants.TAG, "onClick: index"+index);
                    if(index != -1){
                        if(adapter.viewHolderList.get(index).current_added != 3){
                            int sub_index = adapter.viewHolderList.get(index).current_added;
                            if(adapter.viewHolderList.get(index).circleImageViewArrayList.get(sub_index).getVisibility() == View.GONE){
                                adapter.viewHolderList.get(index).circleImageViewArrayList.get(sub_index).setVisibility(View.VISIBLE);
                            } else {
                                Picasso.with(getContext()).load(R.drawable.zed).into(adapter.viewHolderList.get(index).circleImageViewArrayList.get(sub_index));
                            }
                            adapter.viewHolderList.get(index).current_added++;
                        } else {
                            Toast.makeText(getActivity(), "Maximum champions reached", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Who plays this champion? To add a champion, add a username",Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        remove_champion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datasetsize > 0){
                    int index = adapter.get().indexOf(editText.getText().toString());
                    Log.i(Constants.TAG, "onClick: index"+index);
                    if(index != -1){
                        if(adapter.viewHolderList.get(index).current_added != 0){
                            int sub_index = adapter.viewHolderList.get(index).current_added;
                            Log.i(Constants.TAG, "onClick: subindex on del"+sub_index);
                            adapter.viewHolderList.get(index).circleImageViewArrayList.get(sub_index-1).setVisibility(View.GONE);
                            adapter.viewHolderList.get(index).current_added--;
                        } else {
                            Toast.makeText(getActivity(), "Cannot remove any more champions", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Who plays this champion? To add a champion, add a username",Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        save_team_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.get().size() == 0){
                    Toast.makeText(getContext(),"Need at least one player", Toast.LENGTH_SHORT) ;
                    return;
                }
                Toast.makeText(getContext(), "Saved team", Toast.LENGTH_SHORT);
                // TODO: 26/01/2016 Save to database
            }
        });

        toolbar.addView(add_button);
        toolbar.addView(del_button);

        toolbar.addView(add_champion_button);
        toolbar.addView(remove_champion_button);

        toolbar.addView(save_team_button);

        prev_toolbar_title = toolbar.getTitle().toString();
        toolbar.setTitle("");

        recyclerView = (RecyclerView) v.findViewById(R.id.teambuilder_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new ScaleInRightAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbar.removeView(add_button);
        toolbar.removeView(del_button);
        toolbar.removeView(add_champion_button);
        toolbar.removeView(remove_champion_button);
        toolbar.removeView(save_team_button);
        toolbar.setTitle(prev_toolbar_title);
    }
}

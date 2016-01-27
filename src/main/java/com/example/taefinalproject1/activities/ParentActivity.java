package com.example.taefinalproject1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.constants.FragmentConstants;
import com.example.taefinalproject1.fragments.GalleryFragment;
import com.example.taefinalproject1.fragments.HomeFragment;
import com.example.taefinalproject1.fragments.LolKingFragment;
import com.example.taefinalproject1.fragments.MatchListFragment;
import com.example.taefinalproject1.fragments.PlayerEffortFragment;
import com.example.taefinalproject1.fragments.ProBuildsFragment;
import com.example.taefinalproject1.fragments.TeamBuilderFragment;
import com.example.taefinalproject1.logic.FeedbackLogic;
import com.example.taefinalproject1.models.custom.MatchListData;

import java.util.ArrayList;

public class ParentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        HomeFragment homeFragment = new HomeFragment();
//        gotoFragment(FragmentConstants.HOME_FRAGMENT, homeFragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container,homeFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment webview = getSupportFragmentManager().findFragmentByTag(FragmentConstants.PROBUILDS_FRAGMENT);
        if (webview instanceof ProBuildsFragment) {
            boolean goback = ((ProBuildsFragment)webview).canGoBack();
            if (goback){
                ((ProBuildsFragment)webview).goBack();
            } else {
                super.onBackPressed();
            }
        }
        Fragment webview2 = getSupportFragmentManager().findFragmentByTag(FragmentConstants.LOLKING_FRAGMENT);
        if (webview2 instanceof LolKingFragment) {
            boolean goback = ((LolKingFragment)webview).canGoBack();
            if (goback){
                ((LolKingFragment)webview).goBack();
            } else {
                super.onBackPressed();
            }
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {
            GalleryFragment galleryFragment = new GalleryFragment();
            gotoFragment(FragmentConstants.GALLERY_FRAGMENT, galleryFragment);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_fragment_container, galleryFragment).commit();
        } else if (id == R.id.nav_myteam) {
            Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_probuilds) {
            ProBuildsFragment proBuildsFragment = new ProBuildsFragment();
            gotoFragment(FragmentConstants.PROBUILDS_FRAGMENT, proBuildsFragment);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_fragment_container,proBuildsFragment).commit();
        } else if (id == R.id.nav_team_builder){
            TeamBuilderFragment teamBuilderFragment = new TeamBuilderFragment();
            gotoFragment(FragmentConstants.TEAMBUILDER_FRAGMENT, teamBuilderFragment);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_fragment_container,teamBuilderFragment).commit();
        } else if (id == R.id.nav_view_all_matches){
            MatchListFragment matchListFragment = new MatchListFragment();
            gotoFragment(FragmentConstants.MATCHLIST_FRAGMENT,matchListFragment);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_fragment_container, matchListFragment).commit();
        } else if (id == R.id.nav_share){
            FeedbackLogic feedbackLogic = new FeedbackLogic(this);
            feedbackLogic.startMailActivity(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void match_list(){
        Log.i(Constants.TAG, "match_list: function called");
        MatchListFragment matchListFragment = new MatchListFragment();
        gotoFragment(FragmentConstants.MATCHLIST_FRAGMENT, matchListFragment);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_fragment_container, matchListFragment).commit();
    }
    public void player_effort(ArrayList<MatchListData> dataset){
        PlayerEffortFragment playerEffortFragment = new PlayerEffortFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("match_list", dataset);
        playerEffortFragment.setArguments(bundle);
        gotoFragment(FragmentConstants.PLAYEREFFORT_FRAGMENT, playerEffortFragment);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_fragment_container, playerEffortFragment).commit();
    }

    public void grid_item_web_view(String url){
        LolKingFragment lolKingFragment = new LolKingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        lolKingFragment.setArguments(bundle);
        gotoFragment(FragmentConstants.LOLKING_FRAGMENT, lolKingFragment);
    }

    private void gotoFragment(String TAG, Fragment fragment ){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment, TAG)
                .addToBackStack(TAG)
                .commit();
    }
}

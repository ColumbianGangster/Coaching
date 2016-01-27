package com.example.taefinalproject1.persistence;

import android.content.Context;
import android.util.Log;

import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.persistence.teambuilder.Champion;
import com.example.taefinalproject1.persistence.teambuilder.Player;
import com.example.taefinalproject1.persistence.teambuilder.Team;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class MyDatabaseManager {
    private Context context;
    private MyDatabaseHelper myDatabaseHelper;
    private Team team;
    private Player players;
    private Champion champions;

    public MyDatabaseManager(Context context) {
        this.context = context;
    }

    private MyDatabaseHelper getHelper() {
        if (myDatabaseHelper == null) {
            myDatabaseHelper = OpenHelperManager.getHelper(context, MyDatabaseHelper.class);
        }
        return myDatabaseHelper;
    }

    /* Tier 1 functions */

    public void saveTeam(Team team, Player player){
        try {
            Dao dao = getHelper().getTeamDao();
            team.setPlayers(player);
            dao.create(team);
        } catch (SQLException e) {
            Log.i(Constants.TAG, "saveTeam: Error creating team");
            e.printStackTrace();
        }
    }
    public void savePlayer(Player player, Champion champion){
        try {
            Dao dao = getHelper().getPlayerDao();
            player.setChampions(champion);
            dao.create(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveChampion(Champion champion){
        try {
            Dao dao = getHelper().getChampionDao();
            dao.create(champion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Tier 2 Functions */

    public void saveAll(Team team, Player player, Champion champion){
        saveChampion(champion);
        savePlayer(player, champion);
        saveTeam(team, player);
    }

    public void saveAllChampion(LinkedList<Champion> champions){
        for(Champion champion : champions){
            saveChampion(champion);
        }
    }

    public void saveWholePlayer(Player player, LinkedList<Champion> champions){
        for(Champion champion : champions){
            savePlayer(player, champion);
        }
    }

    /*
    This requires that Player <-> Champions have been saved
     */
    public void saveWholeTeam(Team team, LinkedList<Player> players){
        for(Player player: players){
           saveTeam(team, player);
        }
    }
    /*
    Usage:
    Team team = new Team();
    LinkedList<Champion> champions = new LinkedList<Champion>();
    saveAllChampion(champions);

    Player A = new Player();
    LinkedList<Champion> playerAs = new LinkedList<Champion>();
    saveWholePlayer(A, playerAs);

    Player B = new Player();
    LinkedList<Champion> playerBs = new LinkedList<Champion>();
    saveWholePlayer(B, playerBs);

    Player C = new Player();
    LinkedList<Champion> playerCs = new LinkedList<Champion>();
    saveWholePlayer(C, playerCs);

    LinkedList<Player> players = new LinkedList<Player>();
    players.add(A); players.add(B); players.add(C);
    saveWholeTeam(team,players);
     */
    public void saveLocalUser(String username, String password){
        Dao dao = null;
        try {
            dao = getHelper().getLocalUserDao();
            dao.create(new LocalUser(username, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Boolean authenticate(String username, String password){
        try {
            Dao dao = getHelper().getLocalUserDao();
            List<LocalUser> list = dao.queryForAll();
            for(LocalUser localUser : list){
                if(localUser.getUsername().equalsIgnoreCase(username) && localUser.getPassword().equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

package com.example.taefinalproject1.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.persistence.teambuilder.Champion;
import com.example.taefinalproject1.persistence.teambuilder.Player;
import com.example.taefinalproject1.persistence.teambuilder.Team;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class MyDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "localUser.db";
    private static final int DATABASE_VERSION = 1;
//    private Dao<LocalUser,String> localUserDao = null;
//    private Dao<LocalUsersLeagueAccounts, String> usersAccounts = null;
    private Dao<Team, Integer> teamDao = null;
    private Dao<Player, Integer> playerDao = null;
    private Dao<Champion, Integer> championDao = null;
    private Context context;

    public MyDatabaseHelper(Context context, String databaseName, int databaseVersion) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource,LocalUser.class);
            TableUtils.createTableIfNotExists(connectionSource,Team.class);
            TableUtils.createTableIfNotExists(connectionSource,Player.class);
            TableUtils.createTableIfNotExists(connectionSource,Champion.class);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(Constants.TAG, "onCreate: " + "LocalUsers failed to be created");
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource,LocalUsersLeagueAccounts.class);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(Constants.TAG, "onCreate: " + "LocalUsersLeagueAccounts failed to be created");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<Team, Integer> getTeamDao() throws SQLException {
        if (teamDao == null) {
            teamDao = getDao(Team.class);
        }
        return teamDao;
    }

    public Dao<Player, Integer> getPlayerDao() throws SQLException {
        if(playerDao == null) {
            playerDao = getDao(Player.class);
        }
        return playerDao;
    }

    public Dao<Champion, Integer> getChampionDao() throws SQLException {
        if(championDao == null) {
            championDao = getDao(Champion.class);
        }
        return championDao;
    }
}

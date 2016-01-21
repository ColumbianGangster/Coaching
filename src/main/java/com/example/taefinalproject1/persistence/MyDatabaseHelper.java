package com.example.taefinalproject1.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
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
    private Dao<LocalUser,String> localUserDao = null;
    private Dao<LocalUsersLeagueAccounts, String> usersAccounts = null;
    private Context context;

    public MyDatabaseHelper(Context context, String databaseName, int databaseVersion) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource,LocalUser.class);
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
}

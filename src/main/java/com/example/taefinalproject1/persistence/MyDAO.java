package com.example.taefinalproject1.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.taefinalproject1.constants.Constants;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class MyDAO extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "localUser.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<LocalUser,String> localUserDao = null;

    public MyDAO(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // this uses h2 but you can change it to match your database
        String databaseUrl = "jdbc:h2:mem:account";
        // create a connection source to our database
//        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        // instantiate the DAO to handle Account with String id

        try {
            localUserDao = DaoManager.createDao(connectionSource, LocalUser.class);
            // if you need to create the 'LocalUsers' table make this call
            TableUtils.createTable(connectionSource, LocalUser.class);
            // create an instance of LocalUser
            String name = "Jim Smith";
            LocalUser localUser = new LocalUser(name, "_secret");
            // persist the LocalUser object to the database
            localUserDao.create(localUser);
            // retrieve the LocalUser
            LocalUser localUser2 = localUserDao.queryForId(name);
            // show its password
//            System.out.println("LocalUser: " + localUser2.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close the connection source
            connectionSource.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(Constants.TAG, "onCreate: ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.i(Constants.TAG, "onUpgrade: ");
    }
}

package com.example.taefinalproject1.persistence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
@DatabaseTable(tableName = "localusers")
public class LocalUser {

    @DatabaseField(id = true)
    private String username;

    @DatabaseField(canBeNull = false)
    private String password;

    LocalUser(){/* all persisted classes must define a no-arg constructor with at least package visibility */}
    LocalUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

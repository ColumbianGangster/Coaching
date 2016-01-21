package com.example.taefinalproject1.persistence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by TAE_user2 on 21/01/2016.
 */

@DatabaseTable
public class LocalUsersLeagueAccounts {
    public static final String USER_ID_FIELD_NAME = "localUser";

    @DatabaseField(id = true)
    private String inGameName;

    @DatabaseField(canBeNull = false)
    private Boolean isMainAccount;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = USER_ID_FIELD_NAME)
    private LocalUser localUser;

    LocalUsersLeagueAccounts(){/* all persisted classes must define a no-arg constructor with at least package visibility */}
    LocalUsersLeagueAccounts(LocalUser localUser, String inGameName, Boolean isMainAccount){
        this.localUser = localUser;
        this.inGameName = inGameName;
        this.isMainAccount = isMainAccount;
    }
}

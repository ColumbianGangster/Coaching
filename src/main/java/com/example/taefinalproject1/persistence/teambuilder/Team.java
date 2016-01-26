package com.example.taefinalproject1.persistence.teambuilder;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by TAE_user2 on 26/01/2016.
 */
@DatabaseTable(tableName = "Team")
public class Team {
    @DatabaseField(id = true)
    String name;
    @DatabaseField(foreign=true, columnName = "Players")
    Player players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayers() {
        return players;
    }

    public void setPlayers(Player players) {
        this.players = players;
    }

    public Team() {
    }
}

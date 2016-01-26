package com.example.taefinalproject1.persistence.teambuilder;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by TAE_user2 on 26/01/2016.
 */
@DatabaseTable(tableName = "Player")
public class Player {
    @DatabaseField(id = true)
    String name;
    @DatabaseField(canBeNull = false)
    String id;

    public Champion getChampions() {
        return champions;
    }

    public void setChampions(Champion champions) {
        this.champions = champions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DatabaseField(foreign=true, columnName = "Champions")
    Champion champions;

    public Player() {
    }
}

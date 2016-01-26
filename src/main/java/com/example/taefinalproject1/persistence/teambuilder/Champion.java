package com.example.taefinalproject1.persistence.teambuilder;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by TAE_user2 on 26/01/2016.
 */

@DatabaseTable(tableName = "Champion")
public class Champion {
    @DatabaseField(id = true)
    String name;
    @DatabaseField(canBeNull = false)
    String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @DatabaseField(canBeNull = false)
    String url;

    public Champion() {
    }
}


package com.example.taefinalproject1.models.nametoidmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameToIdMap {

    @SerializedName("player")
    @Expose
    private Player player;

    /**
     * 
     * @return
     *     The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 
     * @param player
     *     The player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

}

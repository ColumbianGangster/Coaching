
package com.example.taefinalproject1.models.nametoidmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameToIdMap {

    @SerializedName("nobody42")
    @Expose
    private Nobody42 nobody42;

    /**
     * 
     * @return
     *     The nobody42
     */
    public Nobody42 getNobody42() {
        return nobody42;
    }

    /**
     * 
     * @param nobody42
     *     The nobody42
     */
    public void setNobody42(Nobody42 nobody42) {
        this.nobody42 = nobody42;
    }

}

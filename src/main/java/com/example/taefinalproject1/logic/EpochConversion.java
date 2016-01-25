package com.example.taefinalproject1.logic;

import java.util.Date;

/**
 * Created by TAE_user2 on 23/01/2016.
 */
public class EpochConversion {
    /*
    Convert the League of Legends time stamp to a human readable Date.
     */
    public Date convert(Integer timestamp){
        return new Date(timestamp/1000);
    }
}

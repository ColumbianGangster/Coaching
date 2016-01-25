package com.example.taefinalproject1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class NetworkAvailability {
    public static final String CONNECTED_VIA = "Connected via ";
    public static final String NO_NETWORK_AVAILABLE = "No network available";

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, CONNECTED_VIA + activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(context, CONNECTED_VIA + activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
            return true;
        } else {
            Toast.makeText(context, NO_NETWORK_AVAILABLE, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

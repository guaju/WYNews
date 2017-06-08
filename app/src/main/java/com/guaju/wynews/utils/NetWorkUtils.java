package com.guaju.wynews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by root on 17-6-2.
 */

public class NetWorkUtils {
    private static final String TAG = "NetWorkUtils";
    static boolean hasWifiCon = false;
    static boolean hasMobileCon = false;
    // check all network connect, WIFI or mobile
    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfos = cm.getAllNetworkInfo();
        for (NetworkInfo net : netInfos) {

            String type = net.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                Log.e(TAG, "get Wifi connection");
                if (net.isConnected()) {
                    hasWifiCon = true;
                }
            }

            if (type.equalsIgnoreCase("MOBILE")) {
                   Log.e(TAG, "get Mobile connection");
                if (net.isConnected()) {
                    hasMobileCon = true;
                }
            }
        }
        return hasWifiCon || hasMobileCon;

    }

}

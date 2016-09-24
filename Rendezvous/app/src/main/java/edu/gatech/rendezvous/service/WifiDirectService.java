package edu.gatech.rendezvous.service;

import android.content.Context;

/**
 * Created by jwpilly on 9/24/16.
 */
public class WifiDirectService {
    private static WifiDirectService ourInstance = null;
    private static Context wifiContext;
    private WifiDirectBroadcastReceiver wifiDirectBroadcastReceiver;

    private WifiDirectService(Context context) {
        wifiContext = context;
    }

    public static WifiDirectService getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new WifiDirectService(context);
        }
        return ourInstance;
    }

    public static WifiDirectService getInstance() {
        return ourInstance;
    }
}

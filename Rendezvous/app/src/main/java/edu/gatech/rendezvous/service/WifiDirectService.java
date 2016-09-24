package edu.gatech.rendezvous.service;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by jwpilly on 9/24/16.
 */
public class WifiDirectService {
    private static WifiDirectService ourInstance = null;
    private static Context wifiContext;
    private WifiP2pManager wifiManager;
    private WifiP2pManager.Channel wifiChannel;
    private WifiDirectBroadcastReceiver wifiDirectBroadcastReceiver;

    private void initializeWifiDirect() {

        wifiManager = (WifiP2pManager) wifiContext.getSystemService(Context.WIFI_P2P_SERVICE);
        wifiChannel = wifiManager.initialize(wifiContext, wifiContext.getMainLooper(), null);
    }

    private WifiDirectService(Context context) {
        wifiContext = context;
        // wifiDirectBroadcastReceiver = new WifiDirectBroadcastReceiver();
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

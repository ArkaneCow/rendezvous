package edu.gatech.rendezvous.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;

/**
 * Created by jwpilly on 9/24/16.
 */
public class WifiDirectService {

    public static final int UPDATE_PERIOD = 10000;

    private static WifiDirectService ourInstance = null;
    private static Context wifiContext;
    private WifiP2pManager wifiManager;
    private WifiP2pManager.Channel wifiChannel;
    private WifiDirectBroadcastReceiver wifiDirectBroadcastReceiver;
    private IntentFilter intentFilter;
    private BroadcastReceiver receiver = null;

    private Handler updateHandler = new Handler();

    private Runnable discoverRunnable = new Runnable() {
        @Override
        public void run() {
            wifiDiscover();
            updateHandler.postDelayed(this, UPDATE_PERIOD);
        }
    };

    public WifiDirectBroadcastReceiver getWifiDirectBroadcastReceiver() {
        return wifiDirectBroadcastReceiver;
    }

    private void initializeWifiDirect() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        wifiManager = (WifiP2pManager) wifiContext.getSystemService(Context.WIFI_P2P_SERVICE);
        wifiChannel = wifiManager.initialize(wifiContext, wifiContext.getMainLooper(), null);
        receiver = new WifiDirectBroadcastReceiver(wifiManager, wifiChannel);
        wifiContext.registerReceiver(receiver, intentFilter);
        updateHandler.post(discoverRunnable);
    }

    private void wifiDiscover() {
        wifiManager.discoverPeers(wifiChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int reasonCode) {

            }
        });
    }

    private WifiDirectService(Context context) {
        wifiContext = context;
        initializeWifiDirect();
        wifiDirectBroadcastReceiver = new WifiDirectBroadcastReceiver(wifiManager, wifiChannel);
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

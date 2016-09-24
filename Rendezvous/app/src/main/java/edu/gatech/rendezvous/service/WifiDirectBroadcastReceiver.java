package edu.gatech.rendezvous.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager wifiManager;
    private WifiP2pManager.Channel wifiChannel;

    private List<String> idList;

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    public List<String> getIdList() {
        return idList;
    }
}

package edu.gatech.rendezvous.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager wifiManager;
    private WifiP2pManager.Channel wifiChannel;

    private List<String> addressList;
    private List<String> nameList;
    private String macAddress;


    public WifiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel) {
        super();
        this.wifiManager = manager;
        this.wifiChannel = channel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            wifiRequest();
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (wifiManager != null) {
                wifiRequest();
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            WifiP2pDevice device = (WifiP2pDevice) intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
            macAddress = device.deviceAddress;
        }
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    private void wifiRequest() {
        WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
                List<String> updateAddressList = new ArrayList<>();
                List<String> updateNameList = new ArrayList<>();
                for (WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()) {
                    updateAddressList.add(device.deviceAddress);
                    updateNameList.add(device.deviceName);
                }
                addressList = updateAddressList;
                nameList = updateNameList;
            }
        };
        wifiManager.requestPeers(wifiChannel, peerListListener);
    }
}

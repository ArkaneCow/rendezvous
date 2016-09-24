package edu.gatech.rendezvous.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.service.WifiDirectService;

import java.util.List;

public class AddFriendActivity extends AppCompatActivity {

    private WifiDirectService wifiDirectService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        wifiDirectService = WifiDirectService.getInstance(getApplicationContext());

    }

    public void refreshFriendsNearby(View v) {
        String s = "";
        List<String> idList = wifiDirectService.getWifiDirectBroadcastReceiver().getNameList();
        if (idList == null) {
            Log.v("refreshFriendsNearby", "null");
            return;
        }
        for (String i : wifiDirectService.getWifiDirectBroadcastReceiver().getNameList()) {
            s += i + ",";
        }
        Log.v("refreshFriendsNearby", s);
    }
}

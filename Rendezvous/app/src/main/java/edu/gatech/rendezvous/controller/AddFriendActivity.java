package edu.gatech.rendezvous.controller;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.service.WifiDirectService;

import java.util.ArrayList;
import java.util.List;

public class AddFriendActivity extends AppCompatActivity {

    private WifiDirectService wifiDirectService;
    private ListView friendListView;
    private FriendListAdapter friendListAdapter;
    private List<String> friendsList = new ArrayList<>();

    private Handler friendUpdater = new Handler();
    private Runnable friendUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            List<String> newFriends = refreshList();
            if (newFriends != null) {
                friendsList = newFriends;
            }
            friendListAdapter.updateFriends(friendsList);
            friendListAdapter.notifyDataSetChanged();
            friendUpdater.postDelayed(this, WifiDirectService.UPDATE_PERIOD);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        wifiDirectService = WifiDirectService.getInstance(getApplicationContext());
        friendListView = (ListView) findViewById(R.id.friendListView);
        friendListAdapter = new FriendListAdapter(friendsList, getApplicationContext());
        friendListView.setAdapter(friendListAdapter);
        friendUpdater.post(friendUpdateRunnable);
    }

    private List<String> refreshList() {
        List<String> idList = wifiDirectService.getWifiDirectBroadcastReceiver().getNameList();
        if (idList == null) {
            return null;
        }
        return idList;
    }

    private void updateFriends() {
        List<String> newFriends = refreshList();
        if (newFriends != null) {
            friendsList = newFriends;
        }
        friendListAdapter.updateFriends(friendsList);
        friendListAdapter.notifyDataSetChanged();
    }

    public void refreshFriendsNearby(View v) {
        updateFriends();
    }
}

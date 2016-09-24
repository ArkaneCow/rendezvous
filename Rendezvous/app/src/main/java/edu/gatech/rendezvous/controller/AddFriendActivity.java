package edu.gatech.rendezvous.controller;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    private void setAddUserDialog() {
        //Variables
        final EditText userToAdd;
        Button addButton;
        final Dialog addUserDialog = new Dialog(this);
        addUserDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Process
        addUserDialog.setContentView(R.layout.add_by_user);
        //reminderDialog.setTitle("Plan a Rendezvous");
        userToAdd = (EditText) addUserDialog.findViewById(R.id.userToAdd);
        addButton = (Button) addUserDialog.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userToAdd.getText().toString().equals("testuser")) {
                    Toast.makeText(getApplicationContext(), "You have a new friend!", Toast.LENGTH_SHORT).show();
                    addUserDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "We couldn't find your friend!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addUserDialog.show();
    }


    public void launchAddUserDialog(View v) {
        setAddUserDialog();
    }

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

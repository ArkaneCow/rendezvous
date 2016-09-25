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
import edu.gatech.rendezvous.network.ApiCall;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.command.RendezvousCommandFactory;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousSuccessReceiver;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousUserListReceiver;
import edu.gatech.rendezvous.service.SessionState;
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

    private RendezvousCommandFactory rcf;
    private RendezvousInvoker rci;

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
                ApiCall rapiCall2 = new ApiCall(rcf.getAddFriendCommand(SessionState.getInstance().getSessionUserName(),
                        userToAdd.getText().toString()), new ApiCallback<RendezvousSuccessReceiver>() {
                    @Override
                    public void onReceive(RendezvousSuccessReceiver receiver) {
                        if (receiver.getEntity()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "You have a new friend!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            addUserDialog.dismiss();
                        } else {
                        }
                    }
                });
                rci.executeCall(rapiCall2);

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
        getSupportActionBar().hide();
        rci = RendezvousInvoker.getInstance();
        rcf = RendezvousCommandFactory.getInstance();
    }

    private List<String> refreshList() {
        final List<String> idList = wifiDirectService.getWifiDirectBroadcastReceiver().getNameList();
        if (idList == null) {
            return null;
        }
        List<String> hashList = new ArrayList<>();
        for (String id : idList) {
            hashList.add(WifiDirectService.getInstance().idFunction(id));
        }
        ApiCall rapiCall = new ApiCall(rcf.getProcessIdListCommand(SessionState.getInstance().getSessionUserName(), hashList), new ApiCallback<RendezvousUserListReceiver>() {
            @Override
            public void onReceive(RendezvousUserListReceiver receiver) {
                final List<String> result = receiver.getEntity();
                final List<String> nameList = wifiDirectService.getWifiDirectBroadcastReceiver().getNameList();
                if (result.size() == 0) {
                    return;
                }
                if (nameList.size() == 0) {
                    return;
                }
                for (int i = 0; i < nameList.size(); i++) {
                    if (result.get(i) != null && result.get(i).length() > 0) {
                        nameList.set(i, result.get(i));
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        friendListAdapter.updateFriends(nameList);
                        friendListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        rci.executeCall(rapiCall);
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

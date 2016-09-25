package edu.gatech.rendezvous.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;
import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.network.ApiCall;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.command.RendezvousCommandFactory;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousSuccessReceiver;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousUserListReceiver;
import edu.gatech.rendezvous.service.SessionState;

public class FriendListActivity extends AppCompatActivity {

    private ListView friendsList;
    private UserListAdapter userListAdapter;
    private List<String> fList = new ArrayList<>();

    private RendezvousCommandFactory rcf;
    private RendezvousInvoker rci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        getSupportActionBar().hide();
        rcf = RendezvousCommandFactory.getInstance();
        rci = RendezvousInvoker.getInstance();
        friendsList = (ListView) findViewById(R.id.friendsList);
        userListAdapter = new UserListAdapter(fList, getApplicationContext());
        friendsList.setAdapter(userListAdapter);
        ApiCall rapiCall = new ApiCall(rcf.getFriendListCommand(SessionState.getInstance().getSessionUserName()), new ApiCallback<RendezvousUserListReceiver>() {
            @Override
            public void onReceive(RendezvousUserListReceiver receiver) {
                if (receiver.getEntity() != null && receiver.getEntity().size() != 0) {
                    final List<String> result = receiver.getEntity();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userListAdapter.updateFriends(result);
                            userListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        rci.executeCall(rapiCall);
    }

}

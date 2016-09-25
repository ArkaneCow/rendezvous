package edu.gatech.rendezvous.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        getSupportActionBar().hide();
    }

    private RendezvousCommandFactory rcf;
    private RendezvousInvoker rci;

    ApiCall rapiCall = new ApiCall(rcf.getFriendListCommand(SessionState.getInstance().getSessionUserName()), new ApiCallback<RendezvousUserListReceiver>() {
        @Override
        public void onReceive(RendezvousUserListReceiver receiver) {
            if (receiver.getEntity().size() != 0) {
                ArrayList<String> arrList = (ArrayList<String>) receiver.getEntity();
                ArrayAdapter<String> adapter;
            }
        }
    });




}

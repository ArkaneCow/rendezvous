package edu.gatech.rendezvous.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.model.Reminder;
import edu.gatech.rendezvous.network.ApiCall;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.command.RendezvousCommandFactory;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousReminderListReceiver;
import edu.gatech.rendezvous.service.SessionState;

import java.util.ArrayList;
import java.util.List;

public class ReminderListActivity extends AppCompatActivity {

    private ListView remindersList;
    private ReminderListAdapter reminderListAdapter;
    private List<Reminder> rList = new ArrayList<>();

    private RendezvousCommandFactory rcf;
    private RendezvousInvoker rci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        getSupportActionBar().hide();
        rcf = RendezvousCommandFactory.getInstance();
        rci = RendezvousInvoker.getInstance();
        remindersList = (ListView) findViewById(R.id.reminderList);
        reminderListAdapter = new ReminderListAdapter(rList, getApplicationContext());
        remindersList.setAdapter(reminderListAdapter);
        ApiCall rapiCall = new ApiCall(rcf.getReminderListCommand(SessionState.getInstance().getSessionUserName()), new ApiCallback<RendezvousReminderListReceiver>() {
            @Override
            public void onReceive(RendezvousReminderListReceiver receiver) {
                if (receiver.getEntity() != null && receiver.getEntity().size() != 0) {
                    final List<Reminder> result = receiver.getEntity();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            reminderListAdapter.updateReminders(result);
                            reminderListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        rci.executeCall(rapiCall);
    }
}

package edu.gatech.rendezvous.network.rendezvous.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.rendezvous.model.Reminder;
import edu.gatech.rendezvous.model.User;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiReceiver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousReminderListReceiver extends ApiReceiver<JSONObject, List<Reminder>> {
    public RendezvousReminderListReceiver(RequestFuture requestFuture, ApiCallback responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public List<Reminder> getEntity() {
        final List<Reminder> reminderList = new ArrayList<>();
        final JSONObject jsonRemindersList = getResponse();
        try {
            final boolean jsonReminderSuccess = jsonRemindersList.getBoolean("success");
            final JSONArray jsonReminderList = jsonRemindersList.getJSONArray("reminders");
            if (!jsonReminderSuccess) {
                return reminderList;
            }
            for (int i = 0; i < jsonReminderList.length(); i++) {
                final JSONArray jsonReminder = jsonReminderList.getJSONArray(i);
                final String userReceiver = jsonReminder.getString(0);
                final String userTrigger = jsonReminder.getString(1);
                final String reminderText = jsonReminder.getString(2);
                final int reminderTime = jsonReminder.getInt(3);
                final Reminder reminder = new Reminder(new User(userTrigger), new User(userReceiver), reminderText, reminderTime);
                reminderList.add(reminder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reminderList;
    }
}

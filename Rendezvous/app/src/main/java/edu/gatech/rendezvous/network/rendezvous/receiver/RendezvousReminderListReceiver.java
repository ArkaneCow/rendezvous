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
                final JSONObject jsonReminder = jsonReminderList.getJSONObject(i);
                final JSONObject jsonUserTrigger = jsonReminder.getJSONObject("userTrigger");
                final JSONObject jsonUserReceiver = jsonReminder.getJSONObject("userReceiver");
                final User userTrigger = new User(jsonUserTrigger.getString("username"));
                final User userReceiver = new User(jsonUserReceiver.getString("username"));
                final String reminderText = jsonReminder.getString("reminderText");
                final int reminderTime = jsonReminder.getInt("time");
                final Reminder reminder = new Reminder(userTrigger, userReceiver, reminderText, reminderTime);
                reminderList.add(reminder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reminderList;
    }
}

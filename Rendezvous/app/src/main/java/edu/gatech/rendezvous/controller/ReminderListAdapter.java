package edu.gatech.rendezvous.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.model.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class ReminderListAdapter extends BaseAdapter implements ListAdapter {

    private static LayoutInflater inflater = null;
    private List<Reminder> reminders = new ArrayList<>();
    private Context context;

    public ReminderListAdapter(List<Reminder> reminders, Context context) {
        this.reminders = reminders;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateReminders(List<Reminder> reminders) {
        this.reminders.clear();
        for (Reminder reminder : reminders) {
            this.reminders.add(reminder);
        }
        String s = "";
        for (Reminder f : this.reminders) {
            s += f.getUserTrigger().getUsername() + ",";
        }
        Log.v("updateReminders", s);
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int i) {
        return reminders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView;
        if (view == null) {
            newView = inflater.inflate(R.layout.reminder_list_item, null);
        } else {
            newView = view;
        }
        final TextView userTriggerField = (TextView) newView.findViewById(R.id.userTriggerLabel);
        final TextView reminderTextField = (TextView) newView.findViewById(R.id.reminderTextLabel);
        final Reminder reminder = reminders.get(i);
        userTriggerField.setText(reminder.getUserTrigger().getUsername());
        reminderTextField.setText(reminder.getReminderText());
        return newView;
    }
}

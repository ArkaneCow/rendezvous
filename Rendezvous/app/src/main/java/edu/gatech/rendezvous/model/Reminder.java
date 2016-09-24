package edu.gatech.rendezvous.model;

/**
 * Created by jwpilly on 9/24/16.
 */
public class Reminder {
    private User userTrigger;
    private User userReceiver;
    private String reminderText;
    private int reminderTime;


    public Reminder(User userTrigger, User userReceiver, String reminderText, int reminderTime) {
        this.userTrigger = userTrigger;
        this.userReceiver = userReceiver;
        this.reminderText = reminderText;
        this.reminderTime = reminderTime;
    }

    public User getUserTrigger() {
        return userTrigger;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public String getReminderText() {
        return reminderText;
    }

    public int getReminderTime() {
        return reminderTime;
    }
}

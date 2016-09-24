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

    public void setUserTrigger(User userTrigger) {
        this.userTrigger = userTrigger;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }

    public String getReminderText() {
        return reminderText;
    }

    public void setReminderText(String reminderText) {
        this.reminderText = reminderText;
    }

    public int getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }
}

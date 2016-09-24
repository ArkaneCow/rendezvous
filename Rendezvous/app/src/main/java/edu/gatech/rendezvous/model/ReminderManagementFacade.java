package edu.gatech.rendezvous.model;

/**
 * Created by jwpilly on 9/24/16.
 */
public interface ReminderManagementFacade {
    void addReminder(Reminder reminder);
    Reminder findReminder(User userTrigger, User userReceiver, int reminderTime);
    boolean reminderExists(User userTrigger, User userReceiver, int reminderTime);
    void updateReminder(Reminder reminder);
}

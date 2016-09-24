package edu.gatech.rendezvous.model;

/**
 * Created by jwpilly on 9/24/16.
 */
public class ReminderManager implements ReminderManagementFacade {
    @Override
    public void addReminder(Reminder reminder) {

    }

    @Override
    public Reminder findReminder(User userTrigger, User userReceiver, int reminderTime) {
        return null;
    }

    @Override
    public boolean reminderExists(User userTrigger, User userReceiver, int reminderTime) {
        return false;
    }

    @Override
    public void updateReminder(Reminder reminder) {

    }
}

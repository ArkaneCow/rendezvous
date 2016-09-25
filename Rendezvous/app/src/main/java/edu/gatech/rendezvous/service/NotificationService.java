package edu.gatech.rendezvous.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.controller.MainMenuActivity;
import edu.gatech.rendezvous.model.Reminder;
import edu.gatech.rendezvous.network.ApiCall;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.command.RendezvousCommandFactory;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousReminderListReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class NotificationService {
    public static NotificationService ourInstance = null;
    public static Context notificationContext;

    private WifiDirectService wifiDirectService;
    private Handler notificationHandler;
    private static List<Reminder> reminderList = new ArrayList<>();

    private RendezvousCommandFactory rcf;
    private RendezvousInvoker rci;


    private Runnable notificationUpdate = new Runnable() {
        @Override
        public void run() {
            ApiCall rapiCall = new ApiCall(rcf.getReminderListCommand(SessionState.getInstance().getSessionUserName()), new ApiCallback<RendezvousReminderListReceiver>() {
                @Override
                public void onReceive(RendezvousReminderListReceiver receiver) {
                    if (receiver.getEntity() != null && receiver.getEntity().size() != 0) {
                        final List<Reminder> result = receiver.getEntity();
                        reminderList = result;
                    }
                }
            });
            rci.executeCall(rapiCall);
            notificationHandler.postDelayed(notificationUpdate, WifiDirectService.UPDATE_PERIOD);
        }
    };

    public void executeUserReminder(String username) {
        Log.v("executeUserReminder", username);
        for (Reminder reminder : reminderList) {
            if (reminder.getUserTrigger().getUsername().equals(username)) {
                Log.v("userTrigger", reminder.getUserTrigger().getUsername());
                Log.v("userReceiver", reminder.getUserReceiver().getUsername());
                executeReminder(reminder);
            }
        }
    }

    private void executeReminder(Reminder reminder) {
        Log.v("executingReminder", reminder.getUserTrigger().getUsername());
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(notificationContext)
                        .setSmallIcon(R.drawable.meeting)
                        .setContentTitle(reminder.getReminderText())
                        .setContentText(reminder.getUserTrigger().getUsername());

        NotificationManager notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }


    private NotificationService(Context notificationContext) {
        this.notificationContext = notificationContext;
        wifiDirectService = WifiDirectService.getInstance(notificationContext);
        notificationHandler = new Handler();

        rci = RendezvousInvoker.getInstance();
        rcf = RendezvousCommandFactory.getInstance();

        notificationHandler.post(notificationUpdate);
    }

    public static NotificationService getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new NotificationService(context);
        }
        return ourInstance;
    }

    public static NotificationService getInstance() {
        return ourInstance;
    }


}

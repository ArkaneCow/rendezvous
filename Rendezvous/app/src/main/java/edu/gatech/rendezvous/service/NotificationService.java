package edu.gatech.rendezvous.service;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
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
            rci.executeCall(rapiCall);
            notificationHandler.postDelayed(notificationUpdate, WifiDirectService.UPDATE_PERIOD);
        }
    };

    public void executeUserReminder(String username) {
        for (Reminder reminder : reminderList) {
            if (reminder.getUserReceiver().getUsername().equals(username)) {
                executeReminder(reminder);
            }
        }
    }

    private void executeReminder(Reminder reminder) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(notificationContext)
                        .setContentTitle(reminder.getUserTrigger().getUsername())
                        .setContentText(reminder.getReminderText());
        NotificationManager notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(001, notificationBuilder.build());
    }

    private ApiCall rapiCall;

    private NotificationService(Context notificationContext) {
        this.notificationContext = notificationContext;
        wifiDirectService = WifiDirectService.getInstance(notificationContext);
        notificationHandler = new Handler();

        rci = RendezvousInvoker.getInstance();
        rcf = RendezvousCommandFactory.getInstance();

        rapiCall = new ApiCall(rcf.getReminderListCommand(SessionState.getInstance().getSessionUserName()), new ApiCallback<RendezvousReminderListReceiver>() {
            @Override
            public void onReceive(RendezvousReminderListReceiver receiver) {
                if (receiver.getEntity() != null && receiver.getEntity().size() != 0) {
                    final List<Reminder> result = receiver.getEntity();
                    reminderList = result;
                }
            }
        });
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

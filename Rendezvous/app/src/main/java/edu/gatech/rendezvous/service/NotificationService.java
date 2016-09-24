package edu.gatech.rendezvous.service;

import android.content.Context;
import android.os.Handler;

/**
 * Created by jwpilly on 9/24/16.
 */
public class NotificationService {
    public static NotificationService ourInstance = null;
    public static Context notificationContext;

    private WifiDirectService wifiDirectService;
    private Handler notificationHandler;
    private Runnable notificationUpdate = new Runnable() {
        @Override
        public void run() {

        }
    };

    private NotificationService(Context notificationContext) {
        this.notificationContext = notificationContext;
        wifiDirectService = WifiDirectService.getInstance(notificationContext);
        notificationHandler = new Handler();
    }

    public NotificationService getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new NotificationService(context);
        }
        return ourInstance;
    }


}

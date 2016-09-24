package edu.gatech.rendezvous.service;

import android.content.Context;
import edu.gatech.rendezvous.model.User;

/**
 * Created by jwpilly on 9/24/16.
 */
public class SessionState {
    private static final String SESSION_PREFS = "RENDEZVOUS_SESSION_PREFS";
    private static final String USER_PREFIX = "sessionUser_";
    private static SessionState ourInstance = null;
    private static User sessionUser = null;

    private SessionState() {
    }

    public static SessionState getInstance() {
        if (ourInstance == null) {
            ourInstance = new SessionState();
        }
        return ourInstance;
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    public boolean isLoggedIn() {
        return sessionUser != null;
    }

    public boolean restoreState(Context context) {
        return false;
    }
}

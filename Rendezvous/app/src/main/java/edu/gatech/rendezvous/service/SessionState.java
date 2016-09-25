package edu.gatech.rendezvous.service;

import android.content.Context;
import android.content.SharedPreferences;

import edu.gatech.rendezvous.model.User;

/**
 * Created by jwpilly on 9/24/16.
 */
public class SessionState {
    private static final String SESSION_PREFS = "RENDEZVOUS_SESSION_PREFS";
    private static final String USER_PREFIX = "sessionUser_";
    private static SessionState ourInstance = null;
    private String sessionUserName = null;
    private String sessionApiKey = null;

    private SessionState() {
    }

    public static SessionState getInstance() {
        if (ourInstance == null) {
            ourInstance = new SessionState();
        }
        return ourInstance;
    }

    public String getSessionUserName() {
        return sessionUserName;
    }

    public void setSessionUserName(String sessionUserName) {
        this.sessionUserName = sessionUserName;
    }

    public String getSessionApiKey() {
        return sessionApiKey;
    }

    public void setSessionApiKey(String sessionApiKey) {
        this.sessionApiKey = sessionApiKey;
    }

    public boolean isLoggedIn() {
        return sessionUserName != null;
    }

    public boolean restoreState(Context context) {
        final SharedPreferences saveSession = context.getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        final String username = saveSession.getString(USER_PREFIX + "username", null);
        final String apikey = saveSession.getString(USER_PREFIX + "apikey", null);
        if (username == null || apikey == null) {
            return false;
        } else {
            this.sessionUserName = username;
            this.sessionApiKey = apikey;
            return true;
        }
    }

    public void saveState(Context context) {
        final SharedPreferences saveSession = context.getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = saveSession.edit();
        editor.clear();
        if (sessionUserName != null) {
            editor.putString(USER_PREFIX + "username", sessionUserName);
            editor.putString(USER_PREFIX + "apikey", sessionApiKey);
        }
        editor.apply();
    }
}

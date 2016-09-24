package edu.gatech.rendezvous.model;

import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class User {
    private String username;
    private String password;
    private List<User> friends;
    private List<String> deviceIds;

    public User(String username, String password, List<User> friends, List<String> deviceIds) {
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.deviceIds = deviceIds;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }
}

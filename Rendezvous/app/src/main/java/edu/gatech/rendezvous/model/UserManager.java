package edu.gatech.rendezvous.model;

import java.util.Collection;

/**
 * Created by jwpilly on 9/24/16.
 */
public class UserManager implements AuthenticationFacade, UserManagementFacade {

    @Override
    public User loginUser(String username, String password) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}

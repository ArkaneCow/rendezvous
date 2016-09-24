package edu.gatech.rendezvous.model;

/**
 * Created by jwpilly on 9/24/16.
 */
public class UserManager implements AuthenticationFacade, UserManagementFacade {

    @Override
    public String authenticateUser(String username, String password) {
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
}

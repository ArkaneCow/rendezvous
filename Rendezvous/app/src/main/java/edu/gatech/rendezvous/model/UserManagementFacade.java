package edu.gatech.rendezvous.model;


import java.util.Collection;

/**
 * Created by jwpilly on 9/24/16.
 */
public interface UserManagementFacade {
    void addUser(User user);
    User findUserByUsername(String username);
    boolean userExists(String username);
}

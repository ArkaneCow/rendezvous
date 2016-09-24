package edu.gatech.rendezvous.model;

/**
 * Created by jwpilly on 9/24/16.
 */
public interface AuthenticationFacade {
    String authenticateUser(String username, String password);
}

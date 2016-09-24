package edu.gatech.rendezvous.model;

/**
 * Created by jwpilly on 9/24/16.
 */
public interface AuthenticationFacade {
    User loginUser(String username, String password);
}

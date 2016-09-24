package edu.gatech.rendezvous.network;

/**
 * Created by jwpilly on 9/24/16.
 */
public interface ApiCallback<T extends ApiReceiver> {
    void onReceive(T receiver);
}

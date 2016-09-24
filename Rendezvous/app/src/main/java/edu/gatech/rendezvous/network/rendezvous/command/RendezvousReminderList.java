package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiCommand;
import edu.gatech.rendezvous.network.ApiReceiver;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousReminderList implements ApiCommand {

    private static final String apiEndPoint = "//";
    private String username;

    public RendezvousReminderList(String username) {
        this.username = username;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        return null;
    }
}

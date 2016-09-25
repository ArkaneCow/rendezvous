package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiCommand;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousSuccessReceiver;
import edu.gatech.rendezvous.service.ApiNetwork;
import edu.gatech.rendezvous.service.SessionState;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousUserExists implements ApiCommand {

    private static final String apiEndPoint = "/userExists/";
    private String username;
    private String userQuery;

    public RendezvousUserExists(String userQuery) {
        this.username = SessionState.getInstance().getSessionUserName();
        this.userQuery = userQuery;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint + "/" + userQuery + "/" + username + "/" + RendezvousInvoker.RENDEZVOUS_API_KEY;
        return new RendezvousSuccessReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

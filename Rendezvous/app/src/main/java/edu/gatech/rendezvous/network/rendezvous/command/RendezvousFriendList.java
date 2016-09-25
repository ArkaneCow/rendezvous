package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiCommand;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousUserListReceiver;
import edu.gatech.rendezvous.service.ApiNetwork;
import edu.gatech.rendezvous.service.SessionState;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousFriendList implements ApiCommand {

    private static final String apiEndPoint = "/friendList/";
    private String username;

    public RendezvousFriendList(String username) {
        this.username = username;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint + "/" + username + "/" + SessionState.getInstance().getSessionApiKey();
        return new RendezvousUserListReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

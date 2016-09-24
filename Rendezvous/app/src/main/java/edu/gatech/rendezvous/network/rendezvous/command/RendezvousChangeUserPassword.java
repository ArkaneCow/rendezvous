package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiCommand;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousSuccessReceiver;
import edu.gatech.rendezvous.service.ApiNetwork;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousChangeUserPassword implements ApiCommand {

    private static final String apiEndPoint = "/changePassword/";
    private String username;
    private String oldPassword;
    private String newPassword;

    public RendezvousChangeUserPassword(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint;
        return new RendezvousSuccessReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

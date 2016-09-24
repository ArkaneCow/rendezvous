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
public class RendezvousRegisterUser implements ApiCommand {

    private static final String apiEndPoint = "/register/";
    private String username;
    private String password;

    public RendezvousRegisterUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint + "?apiKey=" + RendezvousInvoker.RENDEZVOUS_API_KEY + "&username=" + username;
        return new RendezvousSuccessReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

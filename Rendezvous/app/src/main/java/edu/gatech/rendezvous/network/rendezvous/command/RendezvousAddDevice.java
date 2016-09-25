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
public class RendezvousAddDevice implements ApiCommand {

    private static final String apiEndPoint = "/addDevice/";
    private String username;
    private String deviceId;

    public RendezvousAddDevice(String username, String deviceId) {
        this.username = username;
        this.deviceId = deviceId;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint + "/" + username + "/" + SessionState.getInstance().getSessionApiKey() + "/" + deviceId;
        return new RendezvousSuccessReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

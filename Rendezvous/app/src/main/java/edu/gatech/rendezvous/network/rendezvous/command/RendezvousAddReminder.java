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
public class RendezvousAddReminder implements ApiCommand {

    private static final String apiEndPoint = "/addReminder/";
    private String userTrigger;
    private String userReceiver;
    private String message;

    public RendezvousAddReminder(String userTrigger, String userReceiver, String message) {
        this.userTrigger = userTrigger;
        this.userReceiver = userReceiver;
        this.message = message;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint + "/" + RendezvousInvoker.RENDEZVOUS_API_KEY + "/" + userReceiver + "/" + userTrigger + "/" + message;
        return new RendezvousSuccessReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

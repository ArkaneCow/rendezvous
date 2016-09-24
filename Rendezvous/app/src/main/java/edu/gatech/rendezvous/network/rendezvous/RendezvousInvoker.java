package edu.gatech.rendezvous.network.rendezvous;

import edu.gatech.rendezvous.network.ApiCall;
import edu.gatech.rendezvous.network.ApiInvoker;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.service.SessionState;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousInvoker implements ApiInvoker {
    public static final String RENDEZVOUS_SERVER = "http://rendezvousserver.azurewebsites.net/";
    public static String RENDEZVOUS_API_KEY = null;
    public static String RENDEZVOUS_USER = null;

    public RendezvousInvoker() {
        RENDEZVOUS_API_KEY = SessionState.getInstance().getSessionApiKey();
        RENDEZVOUS_USER = SessionState.getInstance().getSessionUserName();
    }

    @Override
    public ApiReceiver executeCall(ApiCall call) {
        return call.getApiCommand().execute(call.getApiCallback());
    }
}

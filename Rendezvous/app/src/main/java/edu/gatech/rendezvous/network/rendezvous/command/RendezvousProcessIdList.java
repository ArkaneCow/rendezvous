package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiCommand;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousUserListReceiver;
import edu.gatech.rendezvous.service.ApiNetwork;

import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousProcessIdList implements ApiCommand {

    private static final String apiEndPoint = "/processIds/";
    private String username;
    private List<String> idList;

    public RendezvousProcessIdList(String username, List<String> idList) {
        this.username = username;
        this.idList = idList;
    }

    @Override
    public ApiReceiver execute(ApiCallback callback) {
        final String url = RendezvousInvoker.RENDEZVOUS_SERVER + apiEndPoint;
        StringBuilder sb = new StringBuilder();
        for (String id : idList) {
            sb.append(id + ",");
        }
        String idSerial = sb.toString();
        return new RendezvousUserListReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}

package edu.gatech.rendezvous.network.rendezvous.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiReceiver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousSuccessReceiver extends ApiReceiver<JSONObject, Boolean> {

    public RendezvousSuccessReceiver(RequestFuture requestFuture, ApiCallback responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public Boolean getEntity() {
        final JSONObject jsonResponse = getResponse();
        try {
            final boolean jsonSuccess = jsonResponse.getBoolean("success");
            return jsonSuccess;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}

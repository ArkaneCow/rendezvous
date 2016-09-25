package edu.gatech.rendezvous.network.rendezvous.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousApiKeyReceiver extends ApiReceiver<JSONObject, String> {

    public RendezvousApiKeyReceiver(RequestFuture requestFuture, ApiCallback responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public String getEntity() {
        final JSONObject jsonResponse = getResponse();
        try {
            final boolean jsonSuccess = jsonResponse.getBoolean("success");
            if (!jsonSuccess) {
                return null;
            }
            final String jsonApiKey = jsonResponse.getString("apiKey");
            return jsonApiKey;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

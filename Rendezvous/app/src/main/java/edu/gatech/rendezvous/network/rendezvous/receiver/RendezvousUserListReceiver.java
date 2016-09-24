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
public class RendezvousUserListReceiver extends ApiReceiver<JSONObject, List<String>> {

    public RendezvousUserListReceiver(RequestFuture requestFuture, ApiCallback responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public List<String> getEntity() {
        final List<String> userList = new ArrayList<>();
        final JSONObject jsonusersList = getResponse();
        try {
            final boolean jsonuserSuccess = jsonusersList.getBoolean("success");
            final JSONArray jsonuserList = jsonusersList.getJSONArray("users");
            if (!jsonuserSuccess) {
                return userList;
            }
            for (int i = 0; i < jsonuserList.length(); i++) {
                final String user = jsonuserList.getString(i);
                userList.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userList;
    }
}

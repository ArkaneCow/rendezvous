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
public class RendezvousFriendListReceiver extends ApiReceiver<JSONObject, List<String>> {

    public RendezvousFriendListReceiver(RequestFuture requestFuture, ApiCallback responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public List<String> getEntity() {
        final List<String> friendList = new ArrayList<>();
        final JSONObject jsonFriendsList = getResponse();
        try {
            final boolean jsonFriendSuccess = jsonFriendsList.getBoolean("success");
            final JSONArray jsonFriendList = jsonFriendsList.getJSONArray("friends");
            if (!jsonFriendSuccess) {
                return friendList;
            }
            for (int i = 0; i < jsonFriendList.length(); i++) {
                final String friend = jsonFriendList.getString(i);
                friendList.add(friend);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return friendList;
    }
}

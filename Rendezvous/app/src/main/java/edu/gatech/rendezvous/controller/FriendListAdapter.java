package edu.gatech.rendezvous.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import edu.gatech.rendezvous.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwpilly on 9/24/16.
 */
public class FriendListAdapter extends BaseAdapter implements ListAdapter {

    private static LayoutInflater inflater = null;
    private List<String> friends = new ArrayList<>();
    private Context context;

    public FriendListAdapter(List<String> friends, Context context) {
        this.friends = friends;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateFriends(List<String> friends) {
        this.friends.clear();
        for (String friend : friends) {
            this.friends.add(friend);
        }
        String s = "";
        for (String f : this.friends) {
            s += f + ",";
        }
        Log.v("updateFriends", s);
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int i) {
        return friends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView;
        if (view == null) {
            newView = inflater.inflate(R.layout.friend_list_item, null);
        } else {
            newView = view;
        }
        final TextView listItemText = (TextView) newView.findViewById(R.id.friendLabel);
        final String friendName = friends.get(i);
        listItemText.setText(friendName);
        final Button actionButton = (Button) newView.findViewById(R.id.friendButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("FriendListAdapter", "add " + friendName);
            }
        });
        return newView;
    }
}

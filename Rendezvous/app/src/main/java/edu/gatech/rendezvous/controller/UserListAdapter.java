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
public class UserListAdapter extends BaseAdapter implements ListAdapter {

    private static LayoutInflater inflater = null;
    private List<String> users = new ArrayList<>();
    private Context context;

    public UserListAdapter(List<String> friends, Context context) {
        this.users = friends;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateFriends(List<String> users) {
        this.users.clear();
        for (String user : users) {
            this.users.add(user);
        }
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView;
        if (view == null) {
            newView = inflater.inflate(R.layout.user_list_item, null);
        } else {
            newView = view;
        }
        final TextView listItemText = (TextView) newView.findViewById(R.id.usernameLabel);
        final String userName = users.get(i);
        listItemText.setText(userName);
        return newView;
    }
}

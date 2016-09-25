package edu.gatech.rendezvous.controller;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.rendezvous.R;
import edu.gatech.rendezvous.network.ApiCall;
import edu.gatech.rendezvous.network.ApiCallback;
import edu.gatech.rendezvous.network.ApiReceiver;
import edu.gatech.rendezvous.network.rendezvous.RendezvousInvoker;
import edu.gatech.rendezvous.network.rendezvous.command.RendezvousCommandFactory;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousApiKeyReceiver;
import edu.gatech.rendezvous.network.rendezvous.receiver.RendezvousSuccessReceiver;
import edu.gatech.rendezvous.service.ApiNetwork;
import edu.gatech.rendezvous.service.SessionState;
import edu.gatech.rendezvous.service.WifiDirectService;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private void setReminderDialog() {
        //Variables
        final EditText who;
        final EditText what;
        Button btSave;
        final Dialog reminderDialog = new Dialog(this);
        reminderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Process
        reminderDialog.setContentView(R.layout.activity_set_reminder);
        //reminderDialog.setTitle("Plan a Rendezvous");
        who = (EditText) reminderDialog.findViewById(R.id.who);
        what = (EditText) reminderDialog.findViewById(R.id.what);
        btSave = (Button) reminderDialog.findViewById(R.id.btSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this needs to be changed to check if the user and password are good
               //take the information from who and what and save it into reminders
            }
        });
        reminderDialog.findViewById(R.id.reminderMainLayout).requestFocus();
        reminderDialog.show();
    }

    private RendezvousCommandFactory rcf;
    private RendezvousInvoker rci;

    private void startDialog() {
        //Variables
        final EditText user;
        final EditText password;
        Button btLogin;
        Button btCreate;
        final Dialog dialogCustom = new Dialog(this);
        dialogCustom.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCustom.setCanceledOnTouchOutside(false);
        //Process
        dialogCustom.setContentView(R.layout.activity_login_register);
        //dialogCustom.setTitle("Login Prompt");
        user = (EditText) dialogCustom.findViewById(R.id.enterUser);
        password = (EditText) dialogCustom.findViewById(R.id.enterPass);
        btLogin = (Button) dialogCustom.findViewById(R.id.btLogin);
        btCreate = (Button) dialogCustom.findViewById(R.id.btCreate);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userText = user.getText().toString();
                String passwordText = password.getText().toString();
                ApiCall rapiCall = new ApiCall(rcf.getAuthenticateCommand(userText, passwordText), new ApiCallback<RendezvousApiKeyReceiver>() {
                    @Override
                    public void onReceive(RendezvousApiKeyReceiver receiver) {
                        String apiKey = receiver.getEntity();
                        //this needs to be changed to check if the user and password are good
                        if (apiKey != null) {
                            SessionState.getInstance().setSessionUserName(userText);
                            SessionState.getInstance().setSessionApiKey(apiKey);
                            SessionState.getInstance().saveState(getApplicationContext());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "authentication success", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialogCustom.dismiss();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "authentication failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                if (user.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "user cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "password cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                rci.executeCall(rapiCall);
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "user cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "password cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String userText = user.getText().toString();
                final String passwordText = password.getText().toString();
                ApiCall rapiCall = new ApiCall(rcf.getAuthenticateCommand(userText, passwordText), new ApiCallback<RendezvousApiKeyReceiver>() {
                    @Override
                    public void onReceive(RendezvousApiKeyReceiver receiver) {
                        String apiKey = receiver.getEntity();
                        //this needs to be changed to check if the user and password are good
                        if (apiKey != null) {
                            SessionState.getInstance().setSessionUserName(userText);
                            SessionState.getInstance().setSessionApiKey(apiKey);
                            SessionState.getInstance().saveState(getApplicationContext());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "authentication success", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialogCustom.dismiss();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "authentication failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                ApiCall apiCall1 = new ApiCall(rcf.getRegisterUserCommand(userText, passwordText), new ApiCallback<RendezvousSuccessReceiver>() {
                    @Override
                    public void onReceive(RendezvousSuccessReceiver receiver) {
                        boolean registerSuccess = receiver.getEntity();
                        if (registerSuccess) {
                            rci.executeCall(rapiCall);
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "registration unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                rci.executeCall(apiCall1);
                Toast.makeText(getApplicationContext(), "register success", Toast.LENGTH_SHORT).show();
                dialogCustom.dismiss();
            }
        });

        dialogCustom.show();
    }

    private void initializeServices() {
        ApiNetwork.getInstance(getApplicationContext());
        WifiDirectService.getInstance(getApplicationContext());
        rci = RendezvousInvoker.getInstance();
        rcf = RendezvousCommandFactory.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initializeServices();
        SessionState.getInstance().restoreState(getApplicationContext());
        if (SessionState.getInstance().getSessionUserName() == null || SessionState.getInstance().getSessionApiKey() == null) {
            startDialog();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_plan) {
            setReminderDialog();
        } else if (id == R.id.nav_add) {
            Intent intent = new Intent(this, AddFriendActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_friends) {
            Intent intent = new Intent(this, FriendListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rendez) {
            Intent intent = new Intent(this, ReminderListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

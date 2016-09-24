package edu.gatech.rendezvous.controller;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.rendezvous.R;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private void setReminderDialog() {
        //Variables
        final EditText who;
        final EditText what;
        Button btSave;
        final Dialog reminderDialog = new Dialog(this);

        //Process
        reminderDialog.setContentView(R.layout.activity_set_reminder);
        reminderDialog.setTitle("Plan a Rendezvous");
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
        reminderDialog.show();
    }

    private void startDialog() {
        //Variables
        final EditText user;
        final EditText password;
        Button btLogin;
        Button btCreate;
        final Dialog dialogCustom = new Dialog(this);

        //Process
        dialogCustom.setContentView(R.layout.activity_login_register);
        dialogCustom.setTitle("Login Prompt");
        user = (EditText) dialogCustom.findViewById(R.id.enterUser);
        password = (EditText) dialogCustom.findViewById(R.id.enterPass);
        btLogin = (Button) dialogCustom.findViewById(R.id.btLogin);
        btCreate = (Button) dialogCustom.findViewById(R.id.btCreate);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this needs to be changed to check if the user and password are good
                if (user.getText().toString().equals("User") && password.getText().toString().equals("password")) {
                    Toast.makeText(getApplicationContext(), "Valid credentials", Toast.LENGTH_SHORT).show();

                    dialogCustom.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                    System.out.println(user.getText().toString().equals("User"));
                    System.out.println(password.getText().toString().equals("password"));
                }
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Adding you to the server", Toast.LENGTH_SHORT).show();
                dialogCustom.dismiss();
            }
        });

        dialogCustom.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        startDialog();
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

        } else if (id == R.id.nav_rendez) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

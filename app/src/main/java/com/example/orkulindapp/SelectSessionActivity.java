package com.example.orkulindapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Api.ApiSession;
import Entity.Session;
import Entity.User;

public class SelectSessionActivity extends AppCompatActivity {

    private ApiSession api;
    private List<Session> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_session);

        //Create New Session Button
        FloatingActionButton fab = findViewById(R.id.fab_session);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectSessionActivity.this, SessionActivity.class);
                i.putExtra("Session", new Session());
                startActivityForResult(i,3);
            }
        });


        //List of Sessions
        api = new ApiSession();
        sessions = api.findAllUserSessions(User.user);

        if(sessions.size() == 0 || sessions == null) {
            new AlertDialog.Builder(SelectSessionActivity.this)
                    .setTitle("Create Session")
                    .setMessage("In this section you can create your own session. A session consists of exercises that you have created. If you push the button in the lower right corner you can create new session")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("Back to select Session window", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .show();
        }


        ArrayAdapter adapter = new ArrayAdapter<Session>(this, R.layout.activity_listview, sessions);
        ListView listView = findViewById(R.id.session_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(SelectSessionActivity.this, SessionActivity.class);
                i.putExtra("Session", sessions.get(position));
                startActivityForResult(i,4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, SelectSessionActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
}

package com.example.orkulindapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Api.ApiSession;
import Entity.Session;
import Entity.User;

public class SelectTrainActivity extends AppCompatActivity {

    private ApiSession api;
    private List<Session> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_train);


        //List of Sessions
        api = new ApiSession();
        sessions = api.findAllUserSessions(User.user);
        if(sessions.size() == 0 || sessions == null) {
            new AlertDialog.Builder(SelectTrainActivity.this)
                    .setTitle("Train")
                    .setMessage("In this section you can select a training session to train. Plz create a training session to continue")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("Back to select Training window", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .show();
        }


        ArrayAdapter adapter = new ArrayAdapter<Session>(this, R.layout.activity_listview, sessions);
        ListView listView = findViewById(R.id.training_session_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(SelectTrainActivity.this, TrainActivity.class);
                i.putExtra("Session", sessions.get(position));
                startActivityForResult(i,5);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, SelectTrainActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
}

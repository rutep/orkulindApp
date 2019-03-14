package com.example.orkulindapp;

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

        //Add Session Button
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

        sessions = api.findAllUserSessions(new User());


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

package com.example.orkulindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Entity.User;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        TextView user = (TextView) findViewById(R.id.user);
        user.setText("id: " + User.user.getId() + ", Name: " + User.user.getName() );
    }
}

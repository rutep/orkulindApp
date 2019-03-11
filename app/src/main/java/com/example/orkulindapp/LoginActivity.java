package com.example.orkulindapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Entity.User;
import Api.ApiUser;

import static com.example.orkulindapp.R.id.error;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    public ApiUser api = new ApiUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
            }
        });


        Button createButton = (Button) findViewById(R.id.login);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText)findViewById(R.id.name);
                EditText password = (EditText)findViewById(R.id.password);
                TextView error = (TextView) findViewById(R.id.error);

                // User user = new User(name.getText().toString(),password.getText().toString());

                User user = new User(name.getText().toString(), password.getText().toString());
                user = api.login(user);

                if(user.getError().length() > 0) {
                    name.setError(user.getError());
                    password.setError(user.getError());

                } else {
                    User.user = user;
                    Intent myIntent = new Intent(LoginActivity.this, PersonalActivity.class);
                    startActivity(myIntent);
                }




            }
        });

    }
}


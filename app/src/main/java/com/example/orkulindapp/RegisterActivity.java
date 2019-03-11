package com.example.orkulindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import Api.ApiUser;
import Entity.User;

public class RegisterActivity extends AppCompatActivity {

    ApiUser api = new ApiUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button createButton = (Button) findViewById(R.id.create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText)findViewById(R.id.name);
                EditText password = (EditText)findViewById(R.id.password);
                TextView error = (TextView) findViewById(R.id.error);

                User user = new User(name.getText().toString(),password.getText().toString());

                user = api.register(user);

                if(user.getError().length() > 0) {
                    name.setError(user.getError());
                    password.setError(user.getError());

                } else {
                    error.setText("New user has bin created");
                }

            }
        });
    }
}



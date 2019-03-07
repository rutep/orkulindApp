package com.example.orkulindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import Api.HttpPostRequest;
import Entity.User;

public class RegisterActivity extends AppCompatActivity {

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


                User user = new User(name.getText().toString(),password.getText().toString());

                //Convert user to jsonString
                ObjectMapper mapper = new ObjectMapper();
                String jsonUser = null;
                String response = null;
                try {
                    jsonUser = mapper.writeValueAsString(user);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                HttpPostRequest request = new HttpPostRequest(jsonUser);

                //Post user to server
                try {
                    response = request.execute("register/api").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                try {
                    user = mapper.readValue(response, new TypeReference<User>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }

                name.setText(user.getName());

            }
        });
    }
}



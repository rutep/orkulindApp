package com.example.orkulindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import Api.ApiExercise;
import Api.ApiSession;
import Entity.Exercise;
import Entity.Session;
import Entity.User;

public class SessionActivity extends AppCompatActivity {

    private ApiSession api;
    private ApiExercise exercise_api;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        session = (Session) getIntent().getSerializableExtra("Session");
        api = new ApiSession();

        //Session name
        TextView sessionName = findViewById(R.id.sessionName);
        sessionName.setText(session.getName());

        // Type spinner

        Spinner typeSpinner = (Spinner) findViewById(R.id.sessionType);

        List<String> types = new ArrayList<String>();
        types.add("Type 1");
        types.add("Type 2");

        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapterType);

        int spinnerPosition = dataAdapterType.getPosition(session.getType());
        typeSpinner.setSelection(spinnerPosition);

        //Exercise List

        exercise_api = new ApiExercise();
        final List<Exercise> exercises = exercise_api.findAllUserExercises(new User());


        ArrayAdapter adapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_list_item_single_choice, exercises);
        final ListView listView = findViewById(R.id.session_exercises_list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        if(session.getExercises() != null) {

            int pos = 0;
            for (Exercise exercise: exercises) {
                for(Exercise sess_ex: session.getExercises()){
                    if(sess_ex.getId() == exercise.getId()) {
                        listView.setItemChecked(pos, true);
                    }
                }
                pos+=1;
            }
        }

        //Save and Delete Buttons
        Button saveButton = findViewById(R.id.saveButton_session);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray selectedItems = listView.getCheckedItemPositions();
                List<Exercise> selectedExercises = new ArrayList<Exercise>();
                for (int i = 0; i < selectedItems.size(); i++) {
                    if(selectedItems.get(selectedItems.keyAt(i))){
                        selectedExercises.add(exercises.get(selectedItems.keyAt(i)));
                    }


                }

                String name = ((EditText)findViewById(R.id.sessionName)).getText().toString();
                String type = ((Spinner)findViewById(R.id.sessionType)).getSelectedItem().toString();
                session.setName(name);
                session.setType(type);
                session.setExercises(selectedExercises);
                api.createSession(session);
                setResult(RESULT_OK, null);
                finish();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton_session);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.deleteSession(session);
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}

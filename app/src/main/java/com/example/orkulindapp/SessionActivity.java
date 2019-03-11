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



        exercise_api = new ApiExercise();
        final List<Exercise> exercises = exercise_api.findAllUserExercises(new User());


        ArrayAdapter adapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_list_item_single_choice, exercises);
        final ListView listView = findViewById(R.id.session_exercises_list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        int pos = 0;
        for (Exercise exercise: exercises) {
            for(Exercise sess_ex: session.getExercises()){
                if(sess_ex.getId() == exercise.getId()) {
                    listView.setItemChecked(pos, true);
                }
            }
            pos+=1;
        }


        //Save and Delete Buttons
        Button saveButton = findViewById(R.id.saveButton_session);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray selectedItems = listView.getCheckedItemPositions();
                List<Exercise> selectedExercises = new ArrayList<Exercise>();
                for (int i = 0; i < selectedItems.size(); i++) {
                    selectedExercises.add(exercises.get(selectedItems.keyAt(i)));
                }
                session.setExercises(selectedExercises);
                api.saveSession(session);
                setResult(RESULT_OK, null);
                finish();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton_session);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //api.deleteSession(session);
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}

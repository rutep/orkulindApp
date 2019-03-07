package com.example.orkulindapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Api.ApiExercise;
import Entity.Exercise;

public class ExerciseActivity extends AppCompatActivity {

    private ApiExercise api;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exercise = (Exercise) getIntent().getSerializableExtra("Exercise");
        api = new ApiExercise();

        // Type spinner
        Spinner typeSpinner = (Spinner) findViewById(R.id.exerciseType);

        List<String> types = new ArrayList<String>();
        types.add("Type 1");
        types.add("Type 2");
        types.add("Type 3");

        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapterType);

        // Reptype spinner
        Spinner repTypespinner = (Spinner) findViewById(R.id.exerciseRepType);

        List<String> repTypes = new ArrayList<String>();
        repTypes.add("reps");
        repTypes.add("minutes");

        ArrayAdapter<String> dataAdapterRepType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, repTypes);
        dataAdapterRepType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repTypespinner.setAdapter(dataAdapterRepType);

        //Exercise Inputs
        TextView exerciseName = findViewById(R.id.exerciseName);
        exerciseName.setText(exercise.getName());
        TextView exerciseReps = findViewById(R.id.exerciseReps);
        exerciseReps.setText(Integer.toString(exercise.getReps()));

        //Save and Delete Buttons
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.exerciseName)).getText().toString();
                String type = ((Spinner)findViewById(R.id.exerciseType)).getSelectedItem().toString();
                String repType = ((Spinner)findViewById(R.id.exerciseRepType)).getSelectedItem().toString();
                int reps = Integer.parseInt(((EditText)findViewById(R.id.exerciseReps)).getText().toString());
                String videoLink = ((EditText)findViewById(R.id.exerciseVideoLink)).getText().toString();

                exercise.setName(name);
                exercise.setType(type);
                exercise.setReps(reps);
                exercise.setRepType(repType);
                exercise.setInfo(videoLink);

                api.createExercise(exercise);
                setResult(RESULT_OK, null);
                finish();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.deleteExercise(exercise);
                setResult(RESULT_OK, null);
                finish();
            }
        });

    }
}
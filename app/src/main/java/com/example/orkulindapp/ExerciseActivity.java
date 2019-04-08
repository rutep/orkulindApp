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
import Entity.User;

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

        types.add("Upper Body");
        types.add("Lower Body");
        types.add("Core");
        types.add("Cardio");

        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapterType);

        int typeSpinnerPosition = dataAdapterType.getPosition(exercise.getType());
        typeSpinner.setSelection(typeSpinnerPosition);

        // Reptype spinner
        Spinner repTypeSpinner = (Spinner) findViewById(R.id.exerciseRepType);

        List<String> repTypes = new ArrayList<String>();
        repTypes.add("reps");
        repTypes.add("minutes");

        ArrayAdapter<String> dataAdapterRepType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, repTypes);
        dataAdapterRepType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repTypeSpinner.setAdapter(dataAdapterRepType);

        int repTypeSpinnerPosition = dataAdapterRepType.getPosition(exercise.getRepType());
        repTypeSpinner.setSelection(repTypeSpinnerPosition);

        //Exercise Inputs
        TextView exerciseName = findViewById(R.id.exerciseName);
        exerciseName.setText(exercise.getName());
        TextView exerciseReps = findViewById(R.id.exerciseReps);
        exerciseReps.setText(Integer.toString(exercise.getReps()));

        //Save Button
        Button saveButton = findViewById(R.id.saveButton_exercise);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get inputs
                String name = ((EditText)findViewById(R.id.exerciseName)).getText().toString();
                String type = ((Spinner)findViewById(R.id.exerciseType)).getSelectedItem().toString();
                String repType = ((Spinner)findViewById(R.id.exerciseRepType)).getSelectedItem().toString();
                int reps = Integer.parseInt(((EditText)findViewById(R.id.exerciseReps)).getText().toString());

                //Set inputs
                exercise.setName(name);
                exercise.setType(type);
                exercise.setReps(reps);
                exercise.setRepType(repType);
                exercise.setUserID(User.user.getId());

                // Create Exercise
                api.createExercise(exercise);
                setResult(RESULT_OK, null);
                finish();
            }
        });

        //Delete Button
        Button deleteButton = findViewById(R.id.deleteButton_exercise);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Delete exercise
                api.deleteExercise(exercise);
                setResult(RESULT_OK, null);
                finish();
            }
        });

    }
}
package com.example.orkulindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Api.ApiExercise;
import Entity.Exercise;
import Entity.User;

public class SelectExerciseActivity extends AppCompatActivity {

    private ApiExercise api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);


        String result;

        api = new ApiExercise();

        List<Exercise> exercises = api.findAllUserExercises(new User());

        ArrayAdapter adapter = new ArrayAdapter<Exercise>(this, R.layout.activity_listview, exercises);
        ListView listView = (ListView) findViewById(R.id.exercise_list);
        listView.setAdapter(adapter);
    }
}

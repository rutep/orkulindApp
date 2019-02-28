package com.example.orkulindapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import Api.ApiExercise;
import Entity.Exercise;
import Entity.User;

public class SelectExerciseActivity extends AppCompatActivity {

    private ApiExercise api;
    private List<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);

        //Add Exercise Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //List of Exercises
        String result;

        api = new ApiExercise();

        exercises = api.findAllUserExercises(new User());

        ArrayAdapter adapter = new ArrayAdapter<Exercise>(this, R.layout.activity_listview, exercises);
        ListView listView = (ListView) findViewById(R.id.exercise_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String prufa = exercises.get(position).toString();

                Snackbar.make(view, prufa, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

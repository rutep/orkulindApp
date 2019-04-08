package com.example.orkulindapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //Create new Exercise Button
        FloatingActionButton fab = findViewById(R.id.fab_exercise);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectExerciseActivity.this, ExerciseActivity.class);
                i.putExtra("Exercise", new Exercise());
                startActivityForResult(i,1);
            }
        });

        //List of Exercises
        api = new ApiExercise();
        exercises = api.findAllUserExercises(User.user);
        if(exercises.size() == 0 || exercises == null) {
            // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(SelectExerciseActivity.this);

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Skilaboð")
                    .setTitle("Titill");

            // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
            AlertDialog dialog = builder.create();
        }



        ListView listView = findViewById(R.id.exercise_list);
        ArrayAdapter adapter = new ArrayAdapter<Exercise>(this, R.layout.activity_listview, exercises);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(SelectExerciseActivity.this, ExerciseActivity.class);
                i.putExtra("Exercise", exercises.get(position));
                startActivityForResult(i,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, SelectExerciseActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }

}



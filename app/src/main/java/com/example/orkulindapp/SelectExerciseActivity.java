package com.example.orkulindapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
            new AlertDialog.Builder(SelectExerciseActivity.this)
                    .setTitle("Create Exercise")
                    .setMessage("In this section you can create your own personal exercise. If you push the button in the lower right corner you can create new exercise")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("Back to select Exercise window", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .show();
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



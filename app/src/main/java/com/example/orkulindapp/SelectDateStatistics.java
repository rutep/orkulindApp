package com.example.orkulindapp;

import android.app.DatePickerDialog;
import android.content.Entity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;


import java.util.Calendar;
import java.util.List;

import Api.ApiTrainStatistic;
import Entity.Stats;
import Entity.User;

public class SelectDateStatistics extends AppCompatActivity {

    public static List<Stats> statList;
    EditText startDate, endDate;
    TextView msg;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_statistics);

        startDate = (EditText) findViewById(R.id.startDate);
        endDate = (EditText) findViewById(R.id.endDate);
        msg = (TextView) findViewById(R.id.msg);

        Button buttonStartDate = (Button) findViewById(R.id.buttonStartDate);
        Button buttonEndDate = (Button) findViewById(R.id.buttonEndDate);
        Button buttonStats = (Button) findViewById(R.id.buttonStats);




        buttonStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SelectDateStatistics.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String day, month;

                                monthOfYear += 1;

                                if(monthOfYear < 10) {
                                    month = "0" + monthOfYear;
                                } else {
                                    month = "" + monthOfYear;
                                }

                                if(dayOfMonth < 10) {
                                    day = "0" + dayOfMonth;
                                } else {
                                    day = "" + dayOfMonth;
                                }

                                startDate.setText( year + "-" + month + "-" + day);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        buttonEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SelectDateStatistics.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text

                                monthOfYear += 1;

                                String day, month;

                                if(monthOfYear < 10) {
                                    month = "0" + monthOfYear;
                                } else {
                                    month = "" + monthOfYear;
                                }

                                if(dayOfMonth < 10) {
                                    day = "0" + dayOfMonth;
                                } else {
                                    day = "" + dayOfMonth;
                                }

                                endDate.setText( year + "-" + month + "-" + day);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( startDate.getText().toString().length() > 0) {
                    if(endDate.getText().toString().length() > 0) {
                        Intent intent = new Intent(SelectDateStatistics.this, StatisticActivity.class);

                        Stats stats = new Stats(startDate.getText().toString(),
                                endDate.getText().toString(), User.user.getId() );
                        ApiTrainStatistic statistic = new ApiTrainStatistic();
                        statList = statistic.findStatistics(stats);



                        if(statList.size() == 0){
                            msg.setText("No exercise on given period was found");
                        } else {
                            msg.setText("");
                            startActivity(intent);
                        }

                    } else {

                        msg.setText("Pleas input end date");
                    }
                } else {
                    msg.setText("Pleas input start date");
                }


            }
        });



    }

}

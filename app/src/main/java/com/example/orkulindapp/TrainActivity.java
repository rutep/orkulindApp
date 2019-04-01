package com.example.orkulindapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Api.ApiTrainStatistic;
import Entity.Exercise;
import Entity.Session;
import Entity.Training;


public class TrainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private TrainPagerAdapter mTrainPagerAdapter;

    /**
     * The {@link ViewPager} that will host the training contents.
     */
    private static ViewPager mViewPager;
    private Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        session = (Session) getIntent().getSerializableExtra("Session");

        Toolbar toolbar = (Toolbar) findViewById(R.id.train_toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the sections of the activity.
        mTrainPagerAdapter = new TrainActivity.TrainPagerAdapter(getSupportFragmentManager(), session);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.train_container);
        mViewPager.setAdapter(mTrainPagerAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String SESSION = "session";
        private static final String EXPOS = "expos";
        private Session session;
        private Exercise exercise;
        private int expos;
        private ApiTrainStatistic api;
        private View rootView;


        public PlaceholderFragment() {
        }



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TrainActivity.PlaceholderFragment newInstance(Session session, int expos) {
            TrainActivity.PlaceholderFragment fragment = new TrainActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putSerializable(SESSION, session);
            args.putSerializable(EXPOS, expos);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //Get session and exercise
            session = (Session) getArguments().getSerializable(SESSION);
            expos = (int) getArguments().getSerializable(EXPOS);
            exercise = session.getExercises().get(expos);

            // View
            rootView = inflater.inflate(R.layout.fragment_train, container, false);
            TextView nameView = rootView.findViewById(R.id.train_exercise_name);
            TextView repTypeView = rootView.findViewById(R.id.train_exercise_repType);
            EditText repsView = rootView.findViewById(R.id.train_exercise_reps);
            ImageButton youtubeButton = rootView.findViewById(R.id.youtube_button);

            nameView.setText(exercise.getName());
            repTypeView.setText(exercise.getRepType());
            repsView.setText(Integer.toString(exercise.getReps()));

            youtubeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query="+exercise.getName())));
                }
            });

            // Save button
            Button saveButton = rootView.findViewById(R.id.train_finish_button);

            // Save trainings
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Training training = new Training();
                    training.setExercise(exercise);
                    training.setSession(session);
                    training.setDate(new Date());
                    training.setReps(Integer.parseInt(((EditText)rootView.findViewById(R.id.train_exercise_reps)).getText().toString()));

                    api = new ApiTrainStatistic();
                    api.saveTraining(training);
                    if(expos + 1 == session.getExercises().size()) {
                        getActivity().finish();
                    }
                    else {
                        mViewPager.setCurrentItem(expos+1);
                    }


                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class TrainPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {

        private Session session;

        public TrainPagerAdapter(FragmentManager fm, Session session) {
            super(fm);
            this.session = session;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return TrainActivity.PlaceholderFragment.newInstance(session, position);
        }

        @Override
        public int getCount() {

            return this.session.getExercises().size();
        }
    }
}

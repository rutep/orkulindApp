package com.example.orkulindapp;

import android.content.Intent;
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

import android.widget.TextView;

import java.util.List;

import Entity.Exercise;
import Entity.Session;


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
    private ViewPager mViewPager;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        session = (Session) getIntent().getSerializableExtra("Session");

        Toolbar toolbar = (Toolbar) findViewById(R.id.train_toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
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
        private static final String EXERCISE = "exercise";
        private Exercise exercise;

        public PlaceholderFragment() {
        }



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TrainActivity.PlaceholderFragment newInstance(Exercise exercise) {
            TrainActivity.PlaceholderFragment fragment = new TrainActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putSerializable(EXERCISE, exercise);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_train, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.train_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            exercise = (Exercise) getArguments().getSerializable(EXERCISE);
            textView.setText(exercise.getName());



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
            return TrainActivity.PlaceholderFragment.newInstance(session.getExercises().get(position));
        }

        @Override
        public int getCount() {

            return this.session.getExercises().size();
        }
    }
}

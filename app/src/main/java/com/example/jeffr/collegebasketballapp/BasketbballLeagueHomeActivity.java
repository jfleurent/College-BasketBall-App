package com.example.jeffr.collegebasketballapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.example.jeffr.collegebasketballapp.RecyclerViews.TeamRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BasketbballLeagueHomeActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketbball_league_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basketbball_league_home, menu);
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
    public static class PlaceholderFragment extends Fragment implements RecyclerViewOnClick {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView gender;
        private List<Team> teamList = new ArrayList<>();
        private TeamRecyclerView teamRecyclerView;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_basketbball_league_home, container, false);
            gender = rootView.findViewById(R.id.gender);
            final FragmentActivity fragmentActivity = getActivity();

            //For genders teams data
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1 ){
                gender.setText("Male");
            }
            else{
                gender.setText("Female");
            }

            List<Player> teamPlayers = new ArrayList<>();
            teamPlayers.add(new Player(11,2,"John","Johnson","defence","11"));
            teamPlayers.add(new Player(11,2,"John","Johnson","defence","11"));
            teamPlayers.add(new Player(11,2,"John","Johnson","defence","11"));

            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
            teamRecyclerView = new TeamRecyclerView(teamList,this);
            RecyclerView r = rootView.findViewById(R.id.teams_recyclerview);
            r.setLayoutManager(linearLayoutManager);
            r.setAdapter(teamRecyclerView);
            r.setItemAnimator(new DefaultItemAnimator());

            return rootView;
        }

        @Override
        public void rowSelected(int row) {
            TeamProfileActivity.team = teamList.get(row);
            Intent intent = new Intent(getActivity(),TeamProfileActivity.class);
            startActivity(intent);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}

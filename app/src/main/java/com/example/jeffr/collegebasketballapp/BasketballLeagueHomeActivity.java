package com.example.jeffr.collegebasketballapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.Fragment.PlaceholderFragment;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.example.jeffr.collegebasketballapp.RecyclerViews.TeamRecyclerView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.jeffr.collegebasketballapp.Fragment.PlaceholderFragment.teamList;

public class BasketballLeagueHomeActivity extends AppCompatActivity {

    private PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static ProgressBar progressBar;
    public static String year = "2017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basketbball_league_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


                mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if(tab.equals(tabLayout.getTabAt(0))){
                    PlaceholderFragment.rowNumber = 1;
                }
                else{
                    PlaceholderFragment.rowNumber = 2;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "2017",
                        "2016",
                        "2015",
                        "2014",
                        "2013",
                        "2012",
                        "2011",
                        "2010"
                }));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0 :
                        year = "2017";
                        break;
                    case 1 :
                        year = "2016";
                        break;
                    case 2 :
                        year = "2015";
                        break;
                    case 3 :
                        year = "2014";
                        break;
                    case 4 :
                        year = "2013";
                        break;
                }
                new FetchTeamsTask().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        new FetchTeamsTask().execute(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        teamList.clear();
        return true;
    }
    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Resources.Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Resources.Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basketbball_league_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class FetchTeamsTask extends AsyncTask<Boolean, Void, List<List<Team>>> {

        @Override
        protected List<List<Team>> doInBackground(Boolean... params) {
            List<List<Team>> wholeLeagueOfYear = new ArrayList<>();

            URL teamListRequestUrl1 = NetworkUtils.buildTeamListUrl(true, year);
            URL teamListRequestUrl2 = NetworkUtils.buildTeamListUrl(false, year);

            try {
                String jsonTeamListResponse = NetworkUtils
                        .getResponseFromHttpUrl(teamListRequestUrl1);

                List<Team> teams = TeamListJsonUtils
                        .getTeamsFromJson(BasketballLeagueHomeActivity.this, jsonTeamListResponse);

                wholeLeagueOfYear.add(teams);

                jsonTeamListResponse = NetworkUtils
                        .getResponseFromHttpUrl(teamListRequestUrl2);

                teams = TeamListJsonUtils
                        .getTeamsFromJson(BasketballLeagueHomeActivity.this, jsonTeamListResponse);
                wholeLeagueOfYear.add(teams);

                return wholeLeagueOfYear;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<List<Team>> teamData) {
            teamList = teamData;
            mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }
    }
    }


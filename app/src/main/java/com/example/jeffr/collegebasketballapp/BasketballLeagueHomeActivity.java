package com.example.jeffr.collegebasketballapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeffr.collegebasketballapp.DataObjects.Game;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.Fragment.TeamListFragment;
import com.example.jeffr.collegebasketballapp.Utilities.JsonUtils;
import com.example.jeffr.collegebasketballapp.Utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static com.example.jeffr.collegebasketballapp.Fragment.TeamListFragment.teamList;

public class BasketballLeagueHomeActivity extends AppCompatActivity {

    private TeamListFragment.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ProgressBar progressBar;
    private Spinner spinner;
    public static List<List<Team>> homeTeamData;
    public static String year = "2017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.light_gray));
        setContentView(R.layout.activity_basketbball_league_home);
        progressBar = findViewById(R.id.pb_loading_indicator);
        toolbar = findViewById(R.id.toolbar2);

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new MyTabAdapter());

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new MySpinnerAdapter());
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "2017",
                        "2016",
                        "2015",
                        "2014",
                        "2013",
                }));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setProgressVisiblity(){
        progressBar.setVisibility(View.INVISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        TeamListFragment.teamList = homeTeamData;
        mSectionsPagerAdapter = new TeamListFragment.SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        TeamListFragment.teamList = homeTeamData;
        mSectionsPagerAdapter = new TeamListFragment.SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setBackgroundColor(Color.parseColor("#FFF8E1"));
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
        setIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            List<Team> otherTeamList = null;
            List<List<Team>> newTeamList = new ArrayList<>();
            String query = intent.getStringExtra(SearchManager.QUERY);

                for (List<Team> teams : BasketballLeagueHomeActivity.homeTeamData) {
                    otherTeamList = new ArrayList<>();
                    for (Team team : teams) {

                        if (hasString(team, query)) {
                            otherTeamList.add(team);
                        }
                    }
                    newTeamList.add(otherTeamList);
                }
                if(!newTeamList.get(0).isEmpty()&&!newTeamList.get(1).isEmpty()){
                    TeamListFragment.teamList = newTeamList;
                }
                else{
                    Toast.makeText(this,"Search not found",Toast.LENGTH_LONG).show();
                }

                mSectionsPagerAdapter = new TeamListFragment.SectionsPagerAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mSectionsPagerAdapter);

            }

        else if(homeTeamData != null){
            TeamListFragment.teamList = homeTeamData;
            mSectionsPagerAdapter = new TeamListFragment.SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }

    }

    private boolean hasString(Team team, String query){
        String teamName = team.getName().toLowerCase();
        String teamCity = team.getCity().toLowerCase();
        query = query.toLowerCase();
        if(teamName.equals(query)){
            return true;
        }
        else if(teamCity.equals(query)){
            return true;
        }
        return false;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    public class FetchTeamsTask extends AsyncTask<Boolean, Void, List<List<Team>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = findViewById(R.id.pb_loading_indicator);
            mViewPager = findViewById(R.id.container);
            progressBar.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.INVISIBLE);
        }

        @Override
        protected List<List<Team>> doInBackground(Boolean... params) {
            List<List<Team>> wholeLeagueOfYear = new ArrayList<>();
            List<List<Game>> wholeGamesOfYear = new ArrayList<>();

            URL teamListRequestUrl1 = NetworkUtils.buildTeamListUrl(true, year);
            URL teamListRequestUrl2 = NetworkUtils.buildTeamListUrl(false, year);

            try {

                String jsonTeamListResponse = NetworkUtils
                        .getResponseFromHttpUrl(teamListRequestUrl1);

                List<Team> teams = JsonUtils
                        .getTeamsFromJson(BasketballLeagueHomeActivity.this, jsonTeamListResponse);

                wholeLeagueOfYear.add(teams);

                jsonTeamListResponse = NetworkUtils
                        .getResponseFromHttpUrl(teamListRequestUrl2);

                teams = JsonUtils
                        .getTeamsFromJson(BasketballLeagueHomeActivity.this, jsonTeamListResponse);

                wholeLeagueOfYear.add(teams);

                List<Game> games = JsonUtils
                        .getGamesFromJson(BasketballLeagueHomeActivity.this,true,12,year);

                wholeGamesOfYear.add(games);

                games = JsonUtils
                        .getGamesFromJson(BasketballLeagueHomeActivity.this, false,12,year);

                wholeGamesOfYear.add(games);

                for(int i = 0; i < wholeGamesOfYear.size(); i++){
                    for(int j= 0; j < wholeGamesOfYear.get(i).size(); j++){
                        for(int k = 0; k < wholeLeagueOfYear.get(i).size(); k++) {
                            if (wholeGamesOfYear.get(i).get(j).getHomwId().equals(wholeLeagueOfYear.get(i).get(k).getId())) {
                                wholeLeagueOfYear.get(i).get(k).getTeamGames().add(wholeGamesOfYear.get(i).get(j));
                            }
                        }
                    }
                }

                Collections.sort(wholeLeagueOfYear.get(0));
                Collections.reverse(wholeLeagueOfYear.get(0));
                Collections.sort(wholeLeagueOfYear.get(1));
                Collections.reverse(wholeLeagueOfYear.get(1));
                return wholeLeagueOfYear;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
        protected void onPostExecute(List<List<Team>> teamData) {
            homeTeamData = teamData;
            teamList = homeTeamData;
            mSectionsPagerAdapter = new TeamListFragment.SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mSectionsPagerAdapter);
            setProgressVisiblity();
        }


    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    class MySpinnerAdapter implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch(position){
                case 0 :
                    year = "2017";
                    new FetchTeamsTask().execute();
                    break;
                case 1 :
                    year = "2016";
                    new FetchTeamsTask().execute();
                    break;
                case 2 :
                    year = "2015";
                    new FetchTeamsTask().execute();
                    break;
                case 3 :
                    year = "2014";
                    new FetchTeamsTask().execute();
                    break;
                case 4 :
                    year = "2013";
                    new FetchTeamsTask().execute();
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    class MyTabAdapter implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
            if (tab.equals(tabLayout.getTabAt(0))) {
                TeamListFragment.rowNumber = 1;
            } else {
                TeamListFragment.rowNumber = 2;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
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
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = view.findViewById(android.R.id.text1);
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

    }





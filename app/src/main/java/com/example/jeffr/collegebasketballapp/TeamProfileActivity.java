package com.example.jeffr.collegebasketballapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Team;

import com.example.jeffr.collegebasketballapp.Fragment.TeamInfoFragment;
import com.example.jeffr.collegebasketballapp.Fragment.TeamListFragment;

import static com.example.jeffr.collegebasketballapp.BasketballLeagueHomeActivity.homeTeamData;

public class TeamProfileActivity extends AppCompatActivity {
    public static Team team;
    private TeamInfoFragment.PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_gray));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pagerAdapter = new TeamInfoFragment.PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        BasketballLeagueHomeActivity.searched = false;
        finish();
        return true;
    }




}

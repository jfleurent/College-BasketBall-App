package com.example.jeffr.collegebasketballapp.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.R;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.example.jeffr.collegebasketballapp.RecyclerViews.TeamRecyclerView;
import com.example.jeffr.collegebasketballapp.TeamProfileActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by jeffr on 3/17/2018.
 */

public class TeamListFragment extends Fragment implements RecyclerViewOnClick {

    public  RecyclerView r;
    public  static List<List<Team>> teamList = new ArrayList<>();
    private  static final String ARG_SECTION_NUMBER = "section_number";
    public static int rowNumber = 1;
    public static int listIndex = 0;

    public static boolean male = false;

    private static TeamRecyclerView teamRecyclerView;

    public TeamListFragment() {
    }

    public static TeamListFragment newInstance(int sectionNumber) {
        TeamListFragment fragment = new TeamListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        male = false;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basketbball_league_home, container, false);
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
        r = rootView.findViewById(R.id.teams_recyclerview);
        r.setLayoutManager(linearLayoutManager);
        r.setItemAnimator(new DefaultItemAnimator());
        teamRecyclerView = new TeamRecyclerView(teamList.get(listIndex++%2),TeamListFragment.this);
        r.setAdapter(teamRecyclerView);
        return rootView;
    }

    @Override
    public void rowSelected(int row) {
        if(rowNumber == 1 ){
            TeamProfileActivity.team = teamList.get(0).get(row);
            Intent intent = new Intent(getActivity(),TeamProfileActivity.class);
            intent.putExtra("Male",true);
            startActivity(intent);
        }
        else if(rowNumber == 2 ){
            TeamProfileActivity.team = teamList.get(1).get(row);
            Intent intent = new Intent(getActivity(),TeamProfileActivity.class);
            intent.putExtra("Male",false);
            startActivity(intent);
        }
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TeamListFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {

            return 2;
        }
    }



}



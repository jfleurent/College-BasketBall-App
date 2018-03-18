package com.example.jeffr.collegebasketballapp.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.NetworkUtils;
import com.example.jeffr.collegebasketballapp.R;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.example.jeffr.collegebasketballapp.RecyclerViews.TeamRecyclerView;
import com.example.jeffr.collegebasketballapp.TeamListJsonUtils;
import com.example.jeffr.collegebasketballapp.TeamProfileActivity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffr on 3/17/2018.
 */

public class PlaceholderFragment extends Fragment implements RecyclerViewOnClick {

    private static final String TAG = PlaceholderFragment.class.getSimpleName();
    private RecyclerView r;
    private  List<List<Team>> teamList = new ArrayList<>();
    private static List<PlaceholderFragment> fragments = new ArrayList<>();
    private  static final String ARG_SECTION_NUMBER = "section_number";

    private static TeamRecyclerView teamRecyclerView;

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragments.add(fragment);
        return fragment;
    }

    public static List<PlaceholderFragment> getFragments() {
        return fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basketbball_league_home, container, false);

        final FragmentActivity fragmentActivity = getActivity();


            new FetchTeamsTask().execute(false);

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1 && !teamList.isEmpty() ){
                teamRecyclerView = new TeamRecyclerView(teamList.get(0),PlaceholderFragment.this);
                PlaceholderFragment.newInstance(1).r.setAdapter(teamRecyclerView);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2 && !teamList.isEmpty()){
                teamRecyclerView = new TeamRecyclerView(teamList.get(1),PlaceholderFragment.this);
                PlaceholderFragment.newInstance(2).r.setAdapter(teamRecyclerView);
            }

//            List<Player> teamPlayers = new ArrayList<>();
//            teamPlayers.add(new Player(11,2,"John","Johnson",
//                    "defence","11","02/14/1198","Michigan","67 in",
//                    "220 lbs","41","6","32","30",
//                    "6","73","4","34",
//                    "2","34","65","43",
//                    "65","64","34","54","23",
//                    "43","24","234","34",
//                    "34","43","32","45","23",
//                    "43","53","53","65","23"));
//            teamPlayers.add(new Player(11,2,"John","Johnson",
//                    "defence","11","02/14/1198","Michigan","67 in",
//                    "220 lbs","41","6","32","30",
//                    "6","73","4","34",
//                    "2","34","65","43",
//                    "65","64","34","54","23",
//                    "43","24","234","34",
//                    "34","43","32","45","23",
//                    "43","53","53","65","23"));
//            teamPlayers.add(new Player(11,2,"John","Johnson",
//                    "defence","11","02/14/1198","Michigan","67 in",
//                    "220 lbs","41","6","32","30",
//                    "6","73","4","34",
//                    "2","34","65","43",
//                    "65","64","34","54","23",
//                    "43","24","234","34",
//                    "34","43","32","45","23",
//                    "43","53","53","65","23"));
//            teamPlayers.add(new Player(11,2,"John","Johnson",
//                    "defence","11","02/14/1198","Michigan","67 in",
//                    "220 lbs","41","6","32","30",
//                    "6","73","4","34",
//                    "2","34","65","43",
//                    "65","64","34","54","23",
//                    "43","24","234","34",
//                    "34","43","32","45","23",
//                    "43","53","53","65","23"));
//            teamPlayers.add(new Player(11,2,"John","Johnson",
//                    "defence","11","02/14/1198","Michigan","67 in",
//                    "220 lbs","41","6","32","30",
//                    "6","73","4","34",
//                    "2","34","65","43",
//                    "65","64","34","54","23",
//                    "43","24","234","34",
//                    "34","43","32","45","23",
//                    "43","53","53","65","23"));
//            teamPlayers.add(new Player(11,2,"John","Johnson",
//                    "defence","11","02/14/1198","Michigan","67 in",
//                    "220 lbs","41","6","32","30",
//                    "6","73","4","34",
//                    "2","34","65","43",
//                    "65","64","34","54","23",
//                    "43","24","234","34",
//                    "34","43","32","45","23",
//                    "43","53","53","65","23"));
//
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));
//            teamList.add(new Team(20,12,"Miami","Dolphins",teamPlayers));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        r = rootView.findViewById(R.id.teams_recyclerview);
        r.setLayoutManager(linearLayoutManager);
        r.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override
    public void rowSelected(int row) {
        if(getArguments().getInt(ARG_SECTION_NUMBER) == 1 ){
            TeamProfileActivity.team = teamList.get(0).get(row);
            Log.d(TAG, "Went to row" + getArguments().getInt(ARG_SECTION_NUMBER));

        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2 ){
            TeamProfileActivity.team = teamList.get(1).get(row);
            Log.d(TAG, "Went to row" + getArguments().getInt(ARG_SECTION_NUMBER));

        }

        Intent intent = new Intent(getActivity(),TeamProfileActivity.class);
        startActivity(intent);
    }

    public class FetchTeamsTask extends AsyncTask<Boolean, Void, List<List<Team>>> {

        @Override
        protected List<List<Team>> doInBackground(Boolean... params) {
            List<List<Team>> wholeLeagueOfYear = new ArrayList<>();

            URL teamListRequestUrl1 = NetworkUtils.buildTeamListUrl(true,"2017");
            URL teamListRequestUrl2 = NetworkUtils.buildTeamListUrl(false,"2017");

            try {
                String jsonTeamListResponse = NetworkUtils
                        .getResponseFromHttpUrl(teamListRequestUrl1);

                List<Team> teams = TeamListJsonUtils
                        .getTeamsFromJson(getActivity(), jsonTeamListResponse);

                wholeLeagueOfYear.add(teams);

                jsonTeamListResponse = NetworkUtils
                        .getResponseFromHttpUrl(teamListRequestUrl2);

                teams = TeamListJsonUtils
                        .getTeamsFromJson(getActivity(), jsonTeamListResponse);
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
            PlaceholderFragment fragment1 = fragments.get(0);
            PlaceholderFragment fragment2 = fragments.get(1);
            teamRecyclerView = new TeamRecyclerView(teamList.get(0),PlaceholderFragment.this);
            fragment1.r.setAdapter(teamRecyclerView);
            teamRecyclerView = new TeamRecyclerView(teamList.get(1),PlaceholderFragment.this);
            fragment2.r.setAdapter(teamRecyclerView);

        }
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}



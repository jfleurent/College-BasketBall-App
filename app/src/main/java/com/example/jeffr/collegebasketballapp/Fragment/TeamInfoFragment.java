package com.example.jeffr.collegebasketballapp.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.NetworkUtils;
import com.example.jeffr.collegebasketballapp.PlayerProfileActivity;
import com.example.jeffr.collegebasketballapp.R;
import com.example.jeffr.collegebasketballapp.RecyclerViews.PlayerRecyclerView;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.example.jeffr.collegebasketballapp.TeamListJsonUtils;
import com.example.jeffr.collegebasketballapp.TeamProfileActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
* Created by jeffr on 3/18/2018.
*/
public class TeamInfoFragment extends Fragment implements RecyclerViewOnClick {
    private PieChart pieChart;
    private RecyclerView recyclerView;
    private PlayerRecyclerView playerRecyclerView;
    List<Player> playerList;
    private TextView teamTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_info, container, false);


        pieChart = rootView.findViewById(R.id.team_wins_piechart);
        recyclerView = rootView.findViewById(R.id.team_players_recyclerview);
        teamTitle = rootView.findViewById(R.id.team_name_textview);

        final FragmentActivity fragmentActivity = getActivity();

        new FetchPlayersTask().execute(getActivity().getIntent().getExtras().getBoolean("Male"));

        teamTitle.setText(TeamProfileActivity.team.getName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());



        float teamWinRatio[] = {TeamProfileActivity.team.getWin(),TeamProfileActivity.team.getLoss()};
        String[] winText = {"Win","Loss"};

        addDataSet(teamWinRatio,winText,pieChart);
        return rootView;
    }

    public static TeamInfoFragment newInstance(int sectionNumber) {
        TeamInfoFragment fragment = new TeamInfoFragment();
        Bundle args = new Bundle();
        args.putInt("sectionNumber", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void rowSelected(int row) {
        //TODO Change to bundle usage
        PlayerProfileActivity.playerId = playerList.get(row).getId();
        Intent intent = new Intent(getActivity(),PlayerProfileActivity.class);
        startActivity(intent);
    }

    private void addDataSet(float[] yData, String[] xData, PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Win/Loss");
        pieDataSet.setValueTextSize(0);
        
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        Description description = pieChart.getDescription();
        description.setText("");
        
        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setTouchEnabled(false);
        pieChart.animateY(3000, Easing.EasingOption.EaseInBack);
    }

    public class FetchPlayersTask extends AsyncTask<Boolean, Void, List<Player> > {

        @Override
        protected List<Player> doInBackground(Boolean... params) {

            URL playerListRequestUrl1 = NetworkUtils.buildPlayerListUrl(params[0], TeamProfileActivity.team.getId());

            try {
                String jsonPlayerListResponse = NetworkUtils
                        .getResponseFromHttpUrl(playerListRequestUrl1);

                List<Player> players = TeamListJsonUtils
                        .getPlayersFromJson(getActivity(), jsonPlayerListResponse);

                return players;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(List<Player> playersData) {
            playerList = playersData;
            recyclerView.setAdapter(new PlayerRecyclerView(playersData,TeamInfoFragment.this));
        }
    }
    public static class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TeamInfoFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }


}

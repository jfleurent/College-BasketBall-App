package com.example.jeffr.collegebasketballapp;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.RecyclerViews.PlayerRecyclerView;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.example.jeffr.collegebasketballapp.RecyclerViews.TeamRecyclerView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class TeamProfileActivity extends AppCompatActivity {
    static Team team;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);
    }

    public static class TeamInfoFragment extends Fragment implements RecyclerViewOnClick{
        private PieChart pieChart;
        private RecyclerView recyclerView;
        private PlayerRecyclerView playerRecyclerView;
        private TextView teamTitle;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_team_info, container, false);

            pieChart = rootView.findViewById(R.id.team_wins_piechart);
            recyclerView = rootView.findViewById(R.id.team_players_recyclerview);
            teamTitle = rootView.findViewById(R.id.team_name_textview);

            final FragmentActivity fragmentActivity = getActivity();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
            playerRecyclerView = new PlayerRecyclerView(team.getTeamPlayers(),this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(playerRecyclerView);
            recyclerView.setItemAnimator(new DefaultItemAnimator());



            float teamWinRatio[] = {team.getWin(),team.getLoss()};
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

        }
    }


    public class PagerAdapter extends FragmentPagerAdapter {

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

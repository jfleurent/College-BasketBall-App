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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.RecyclerViews.PlayerRecyclerView;
import com.example.jeffr.collegebasketballapp.RecyclerViews.RecyclerViewOnClick;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class PlayerProfileActivity extends AppCompatActivity {
    static Player player;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public static class PlayerInfoFragment extends Fragment {
        private PieChart pieChart;
        private TextView jerseyNumber;
        private TextView playerName;
        private TextView playerDOB;
        private TextView playerBirthPlace;
        private TextView playerHeight;
        private TextView playerWeight;
        private TextView playerPosition;
        private TextView totalAssists;
        private TextView assistsPerGame;
        private TextView blockAttempts;
        private TextView blockAttemptsPerGame;
        private TextView blockedShots;
        private TextView blockedShotsPerGame;
        private TextView totalRebounds;
        private TextView reboundsPerGame;
        private TextView defensiveREbounds;
        private TextView defensiveReboundsPerGame;
        private TextView turnoverAttempts;
        private TextView turnoversPerGame;
        private TextView turnoverToAssist;
        private TextView totalFreeThrow;
        private TextView freeThrowPerGame;
        private TextView successfulFreeThrow;
        private TextView sucessfulFreeThrowPerGame;
        private TextView freeThrowPercentage;
        private TextView totalTwoPointer;
        private TextView twoPointerPerGame;
        private TextView sucessfulTwoPointer;
        private TextView sucessfulTwoPointerPerGame;
        private TextView twoPointerPercentage;
        private TextView totalThreePointer;
        private TextView threePointerPerGame;
        private TextView sucessfulThreePointer;
        private TextView sucessfulThreePointerPerGame;
        private TextView threePointerPercentage;
        private TextView totalTrueShot;
        private TextView trueShotPerGame;
        private TextView trueShotPercentage;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_player_info, container, false);

            pieChart = rootView.findViewById(R.id.player_play_time_piechart);
            jerseyNumber = rootView.findViewById(R.id.player_number_textview);
            playerBirthPlace = rootView.findViewById(R.id.player_birth_place_textview);
            playerDOB = rootView.findViewById(R.id.player_DOB_textview);
            playerHeight = rootView.findViewById(R.id.player_hieght_textview);
            playerWeight = rootView.findViewById(R.id.player_weight_textview);
            playerPosition = rootView.findViewById(R.id.player_position_textview);
            totalAssists = rootView.findViewById(R.id.total_assists_textview);
            assistsPerGame = rootView.findViewById(R.id.per_game_assists_textview);
            turnoverToAssist = rootView.findViewById(R.id.turnover_assists_textview);
            blockAttempts = rootView.findViewById(R.id.total_blocks_textview);
            blockAttemptsPerGame = rootView.findViewById(R.id.per_game_blocks_textview);
            blockedShots = rootView.findViewById(R.id.shot_blocks_textview);
            blockedShotsPerGame = rootView.findViewById(R.id.per_game_shot_blocks_textview);
            totalRebounds = rootView.findViewById(R.id.total_rebounds_textview);
            reboundsPerGame = rootView.findViewById(R.id.per_game_rebounds_textview);
            defensiveREbounds = rootView.findViewById(R.id.defencive_rebounds_textview);
            defensiveReboundsPerGame = rootView.findViewById(R.id.per_game_defensive_rebounds_textview);
            turnoverAttempts = rootView.findViewById(R.id.total_turnovers_textview);
            turnoversPerGame = rootView.findViewById(R.id.per_game_turnover_textview);
            freeThrowPercentage = rootView.findViewById(R.id.free_throw_percentage_textview);
            freeThrowPerGame = rootView.findViewById(R.id.per_game_free_throws_textview);
            successfulFreeThrow = rootView.findViewById(R.id.made_free_throw_textview);
            sucessfulFreeThrowPerGame = rootView.findViewById(R.id.made_free_throw_per_game_textview);
            totalFreeThrow = rootView.findViewById(R.id.total_free_throws_textview);
            twoPointerPercentage = rootView.findViewById(R.id.percentage_two_pointer_textview);
            twoPointerPerGame = rootView.findViewById(R.id.per_game_two_pointer_textview);
            sucessfulTwoPointer = rootView.findViewById(R.id.made_two_pointer_textview);
            sucessfulTwoPointerPerGame = rootView.findViewById(R.id.made_per_game_two_pointer_textview);
            totalTwoPointer = rootView.findViewById(R.id.total_two_pointers_textview);
            threePointerPercentage = rootView.findViewById(R.id.percentage_three_pointer_textview);
            threePointerPerGame = rootView.findViewById(R.id.per_game_three_pointer_textview);
            sucessfulThreePointer = rootView.findViewById(R.id.made_three_pointer_textview);
            sucessfulThreePointerPerGame = rootView.findViewById(R.id.made_per_game_three_pointer_textview);
            totalThreePointer = rootView.findViewById(R.id.total_three_pointers_textview);
            trueShotPercentage = rootView.findViewById(R.id.percentage_true_shot_textview);
            trueShotPerGame = rootView.findViewById(R.id.per_game_true_shot_textview);
            totalTrueShot = rootView.findViewById(R.id.total_true_shot_textview);


            float teamWinRatio[] = {player.getTimePlayed(),player.getTimeNotPlayed()};
            String[] winText = {"Win","Loss"};

            addDataSet(teamWinRatio,winText,pieChart);
            return rootView;
        }

        public static PlayerInfoFragment newInstance(int sectionNumber) {
            PlayerInfoFragment fragment = new PlayerInfoFragment();
            Bundle args = new Bundle();
            args.putInt("sectionNumber", sectionNumber);
            fragment.setArguments(args);
            return fragment;
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


    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlayerInfoFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}

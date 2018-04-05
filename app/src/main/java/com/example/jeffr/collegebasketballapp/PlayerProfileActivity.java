package com.example.jeffr.collegebasketballapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.Utilities.JsonUtils;
import com.example.jeffr.collegebasketballapp.Utilities.NetworkUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.net.URL;
import java.util.ArrayList;

public class PlayerProfileActivity extends AppCompatActivity {
    public static String playerId;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_gray));
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public static class PlayerInfoFragment extends Fragment {
        private PieChart pieChart;
        private TextView jerseyNumber;
        private TextView playerName;
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
            playerName = rootView.findViewById(R.id.player_name_textview);
            jerseyNumber = rootView.findViewById(R.id.player_number_textview);
            playerBirthPlace = rootView.findViewById(R.id.player_birth_place_textview);
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
            new FetchTeamsTask().execute(getActivity().getIntent().getExtras().getBoolean("Male"));
            return rootView;
        }

        public static PlayerInfoFragment newInstance(int sectionNumber) {
            PlayerInfoFragment fragment = new PlayerInfoFragment();
            Bundle args = new Bundle();
            args.putInt("sectionNumber", sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        private void addDataSet(int[] yData, String[] xData, PieChart pieChart) {
            ArrayList<PieEntry> yEntrys = new ArrayList<>();
            ArrayList<String> xEntrys = new ArrayList<>();

            String[] start = {"Start","Not Start"};
            for(int i = 0; i < yData.length; i++){
                yEntrys.add(new PieEntry(yData[i] , start[i]));
            }

            for(int i = 1; i < xData.length; i++){
                xEntrys.add(xData[i]);
            }

            PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
            pieDataSet.setValueTextSize(15);

            ArrayList<Integer> colors = new ArrayList<>();
            colors.add(Color.GREEN);
            colors.add(Color.RED);

            pieDataSet.setColors(colors);

            Legend legend = pieChart.getLegend();
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
            legend.setTextSize(10);
            legend.setForm(Legend.LegendForm.SQUARE);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

            Description description = pieChart.getDescription();
            description.setText("");

            PieData pieData = new PieData(pieDataSet);

            pieChart.setData(pieData);
            pieChart.invalidate();
            pieChart.setCenterText("Start Ratio");
            pieChart.setCenterTextSize(20);
            pieChart.setRotationEnabled(false);
            pieChart.animateY(3000, Easing.EasingOption.EaseInBack);

        }
        public class FetchTeamsTask extends AsyncTask<Boolean, Void, Player> {

            @Override
            protected Player doInBackground(Boolean... params) {
                Player player = null;

                URL playerRequestUrl1 = NetworkUtils.buildPlayerUrl(params[0],playerId);

                try {
                    String jsonPlayerResponse = NetworkUtils
                            .getResponseFromHttpUrl(playerRequestUrl1);

                    player = JsonUtils
                            .getPlayerFromJson(getActivity(), jsonPlayerResponse,2017,TeamProfileActivity.team.getId());

                    return player;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Player playerData) {
                try {
                    int teamWinRatio[] = {playerData.getTimePlayed(), playerData.getTimeNotPlayed()};
                    String[] winText = {"Win", "Loss"};
                    addDataSet(teamWinRatio, winText, pieChart);
                    String number = playerData.getNumber().length() > 1 ? playerData.getNumber() : "0" + playerData.getNumber();
                    jerseyNumber.setText(number);
                    playerName.setText("Name: " + playerData.getFirstName() + " " + playerData.getLastName());
                    playerBirthPlace.setText("Birth Place: " + playerData.getBirthPlace());
                    playerHeight.setText("Height: " + playerData.getHeight() + " in");
                    String weight = getActivity().getIntent().getExtras().getBoolean("Male") ?
                            "Weight: " + playerData.getWeight() + " lbs" : "Weight: " + playerData.getWeight();
                    playerWeight.setText(weight);
                    playerPosition.setText("Position: " + playerData.getPosition());
                    totalAssists.setText("Total: " + playerData.getTotalAssists());
                    assistsPerGame.setText("Per Game: " + playerData.getAssistsPerGame());
                    blockAttempts.setText("Total Attempts :" + playerData.getBlockAttempts());
                    blockAttemptsPerGame.setText("Attempts Per Game: " + playerData.getBlockAttemptsPerGame());
                    blockedShots.setText("Blocked Shots: " + playerData.getSucessfulBlocks());
                    blockedShotsPerGame.setText("Blocked Shots Per Game: " + playerData.getSucessfulBlocksPerGame());
                    totalRebounds.setText("Total Attempts: " + playerData.getTotalRebounds());
                    reboundsPerGame.setText("Attempts Per Game: " + playerData.getReboundsPerGame());
                    defensiveREbounds.setText("Defensive Rebounds: " + playerData.getDefensiveRebounds());
                    defensiveReboundsPerGame.setText("Defensive Rebounds Per Game: " + playerData.getDefensiveReboundsPerGame());
                    turnoverAttempts.setText("Total Attempts: " + playerData.getTurnovers());
                    turnoversPerGame.setText("Attempts Per Game: " + playerData.getTurnoverPerGame());
                    turnoverToAssist.setText("Assists to Turnover Ratio: " + playerData.getTurnoverToAssist());
                    totalFreeThrow.setText("Total: " + playerData.getFreeThrowsAttempts());
                    freeThrowPerGame.setText("Per Game: " + playerData.getSucessfulFreeThrowPerGame());
                    successfulFreeThrow.setText("Free Throws Made: " + playerData.getSucessfulfreeThrow());
                    sucessfulFreeThrowPerGame.setText("Free Throws Made Per Game: " + playerData.getSucessfulFreeThrowPerGame());

                    double percentage = Double.valueOf(playerData.getFreeThrowPercentage())*100;
                    freeThrowPercentage.setText("Free Throw Percentage :" + percentage+"%");
//
                    totalTwoPointer.setText("Total Attempts: " + playerData.getTwoPointAttempts());
                    twoPointerPerGame.setText("Attempts Per Game: " + playerData.getTwoPointAttemptsPerGame());
                    sucessfulTwoPointer.setText("Two Pointers Made: " + playerData.getSucessfulTwoPoint());
                    sucessfulTwoPointerPerGame.setText("Two Pointers Made Per Game: " + playerData.getSucessfulTwoPointPerGame());

                    percentage = Double.valueOf(playerData.getTwoPointAttemptsPercentage())*100;
                    twoPointerPercentage.setText("Two Pointer Percentage: " + percentage+"%");

                    percentage = Double.valueOf(playerData.getThreePointPercentage())*100;
                    threePointerPercentage.setText("Three Pointer Percentage: " + percentage+"%");

                    threePointerPerGame.setText("Three Pointers Per Game: " + playerData.getThreePointAttemptsPerGame());
                    sucessfulThreePointer.setText("Three Pointers Made: " + playerData.getSucessfulThreePoint());
                    sucessfulThreePointerPerGame.setText("Three Pointer Made Per Game: " + playerData.getSucessfulThreePointPerGame());
                    totalThreePointer.setText("Total Attempts: " + playerData.getThreePointAttempts());

                    percentage = Double.valueOf(playerData.getTrueShotPercentage())*100;
                    trueShotPercentage.setText("True Shot Percentage: " + percentage+"%");

                    trueShotPerGame.setText("Attempts Per Game: " + playerData.getTrueShotAttemptsPerGame());
                    totalTrueShot.setText("Total Attempts: " + playerData.getTrueShotAttempts());
                }
                catch (Exception e){
                }

            }
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

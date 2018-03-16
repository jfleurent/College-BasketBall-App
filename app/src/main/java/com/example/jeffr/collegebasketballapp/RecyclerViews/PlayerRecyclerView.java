package com.example.jeffr.collegebasketballapp.RecyclerViews;

import android.content.Context;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffr on 3/15/2018.
 */

public class PlayerRecyclerView extends RecyclerView.Adapter<PlayerRecyclerView.PlayerRecyclerViewHolder> {
    RecyclerViewOnClick recyclerViewOnClick;
    List<Player> playerList = new ArrayList<>();

    public PlayerRecyclerView(List<Player> playerList,RecyclerViewOnClick recyclerViewOnClick) {
        this.recyclerViewOnClick = recyclerViewOnClick;
        this.playerList = playerList;
    }

    @Override
    public PlayerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.player_info_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new PlayerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlayerRecyclerViewHolder holder, int position) {
        float[] playTime = {playerList.get(position).getTimePlayed(),playerList.get(position).getTimeNotPlayed()};
        String[] pieChartItems = {"Time Played","Time Not Played"};
        holder.playerPosition.setText(playerList.get(position).getPosition());
        holder.playerNumber.setText(playerList.get(position).getNumber());
        holder.playerLastName.setText(playerList.get(position).getLastName());
        holder.playerFirstName.setText(playerList.get(position).getFirstName());
        addDataSet(playTime,pieChartItems,holder.playTimeChart);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnClick.rowSelected(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
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

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    public class PlayerRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView playerNumber;
        TextView playerPosition;
        TextView playerFirstName;
        TextView playerLastName;
        PieChart playTimeChart;

        public PlayerRecyclerViewHolder(View view) {
            super(view);
            playerFirstName = view.findViewById(R.id.first_name_textview);
            playerLastName = view.findViewById(R.id.last_name_textview);
            playerNumber = view.findViewById(R.id.player_nunber_textview);
            playerPosition = view.findViewById(R.id.player_position_textview);
            playTimeChart = view.findViewById(R.id.play_time_piechart);



        }
    }
}

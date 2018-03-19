package com.example.jeffr.collegebasketballapp.RecyclerViews;

import android.content.Context;
import android.graphics.Color;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;
import com.example.jeffr.collegebasketballapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffr on 3/15/2018.
 */

public class TeamRecyclerView extends RecyclerView.Adapter<TeamRecyclerView.TeamRecyclerViewHolder> {
    RecyclerViewOnClick recyclerViewOnClick;
    List<Team> teamList = new ArrayList<>();

    public TeamRecyclerView(List<Team> teamList,RecyclerViewOnClick recyclerViewOnClick) {
        this.recyclerViewOnClick = recyclerViewOnClick;
        this.teamList = teamList;
    }

    @Override
    public TeamRecyclerView.TeamRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.team_info_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new TeamRecyclerView.TeamRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamRecyclerView.TeamRecyclerViewHolder holder, int position) {
        float[] winRatio = {teamList.get(position).getWin(),teamList.get(position).getLoss()};
        String[] winText = {"Win","Loss"};
        holder.teamCity.setText(teamList.get(position).getCity());
        holder.teamName.setText(teamList.get(position).getName());
        addDataSet(winRatio,winText,holder.winChance);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnClick.rowSelected(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamList.size();
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
        legend.setEnabled(false);


        Description description = pieChart.getDescription();
        description.setText("");




        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setTouchEnabled(false);

    }

    public class TeamRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView teamName;
        TextView teamCity;
        PieChart winChance;

        public TeamRecyclerViewHolder(View view) {
            super(view);
            teamName = view.findViewById(R.id.team_name_textview);
            teamCity = view.findViewById(R.id.team_city_textview);
            winChance = view.findViewById(R.id.win_loss_piechart);
        }
    }
}

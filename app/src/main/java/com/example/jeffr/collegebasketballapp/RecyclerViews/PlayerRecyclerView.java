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
        holder.playerPosition.setText("Position: "+playerList.get(position).getPosition());
        String number = playerList.get(position).getNumber().length() > 1 ?playerList.get(position).getNumber() :
                "0"+playerList.get(position).getNumber();
        holder.playerNumber.setText(number);
        holder.playerLastName.setText("Last Name: "+playerList.get(position).getLastName());
        holder.playerFirstName.setText("First Name: "+playerList.get(position).getFirstName());
        holder.playerExperience.setText(playerList.get(position).getExperience());
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

    public class PlayerRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView playerNumber;
        TextView playerPosition;
        TextView playerFirstName;
        TextView playerLastName;
        TextView playerExperience;

        public PlayerRecyclerViewHolder(View view) {
            super(view);
            playerFirstName = view.findViewById(R.id.first_name_textview);
            playerLastName = view.findViewById(R.id.last_name_textview);
            playerNumber = view.findViewById(R.id.player_nunber_textview);
            playerPosition = view.findViewById(R.id.player_position_textview);
            playerExperience = view.findViewById(R.id.player_experience_textview);
        }
    }
}

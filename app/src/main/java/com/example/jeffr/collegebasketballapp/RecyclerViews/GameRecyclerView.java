package com.example.jeffr.collegebasketballapp.RecyclerViews;

/**
 * Created by jeffr on 3/20/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeffr.collegebasketballapp.DataObjects.Game;
import com.example.jeffr.collegebasketballapp.R;

import java.util.ArrayList;
import java.util.List;


public class GameRecyclerView extends RecyclerView.Adapter<GameRecyclerView.GameRecyclerViewHolder> {
    RecyclerViewOnClick recyclerViewOnClick;
    List<Game> gameList = new ArrayList<>();

    public GameRecyclerView(List<Game> gameList,RecyclerViewOnClick recyclerViewOnClick) {
        this.recyclerViewOnClick = recyclerViewOnClick;
        this.gameList = gameList;
    }

    @Override
    public GameRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.game_data_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new GameRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GameRecyclerViewHolder holder, int position) {
        holder.homeScore.setText(String.valueOf(gameList.get(position).getHomeScore()));
        holder.homeName.setText(gameList.get(position).getHomeName());
        holder.awayScore.setText(String.valueOf(gameList.get(position).getAwayScore()));
        holder.awayName.setText(gameList.get(position).getAwayName());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnClick.rowSelected(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class GameRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView homeName;
        TextView homeScore;
        TextView awayName;
        TextView awayScore;

        public GameRecyclerViewHolder(View view) {
            super(view);
            homeName = view.findViewById(R.id.home_name_textview);
            homeScore = view.findViewById(R.id.home_score_textview);
            awayName = view.findViewById(R.id.away_name_textview);
            awayScore = view.findViewById(R.id.away_score_textview);
        }
    }
}


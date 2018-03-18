package com.example.jeffr.collegebasketballapp.DataObjects;

import java.util.List;

/**
 * Created by jeffr on 3/15/2018.
 */

public class Team {
    private String id;
    private int win;
    private int loss;
    private String city;
    private String name;
    private List<Player> teamPlayers;

    public Team(int win, int loss, String city, String name,List<Player> teamPlayers, String id){
        this.win = win;
        this.loss = loss;
        this.city = city;
        this.name = name;
        this.teamPlayers = teamPlayers;
        this.id = id;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}

package com.example.jeffr.collegebasketballapp.DataObjects;

/**
 * Created by jeffr on 3/20/2018.
 */

public class Game {
    String homwId;
    String homeName;
    String awayName;
    int homeScore;
    int awayScore;

    public Game(String homeId, String homeName,String awayName, int homeScore, int awayScore){
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeScore = homeScore;
        this.homwId = homeId;
        this.awayScore = awayScore;
    }



    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public String getHomwId() {
        return homwId;
    }

    public void setHomwId(String homwId) {
        this.homwId = homwId;
    }
}

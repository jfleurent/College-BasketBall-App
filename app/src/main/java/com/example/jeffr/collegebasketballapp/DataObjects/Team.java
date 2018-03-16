package com.example.jeffr.collegebasketballapp.DataObjects;

/**
 * Created by jeffr on 3/15/2018.
 */

public class Team {
    private int win;
    private int loss;
    private String city;
    private String name;

    public Team(int win, int loss, String city, String name){
        this.win = win;
        this.loss = loss;
        this.city = city;
        this.name = name;
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

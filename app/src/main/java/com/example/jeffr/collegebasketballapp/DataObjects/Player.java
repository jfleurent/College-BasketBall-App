package com.example.jeffr.collegebasketballapp.DataObjects;

/**
 * Created by jeffr on 3/15/2018.
 */

public class Player {
    private float timePlayed;
    private float timeNotPlayed;
    private String firstName;
    private String lastName;
    private String position;
    private String number;

    public Player(float timePlayed, float timeNotPlayed, String firstName, String lastName, String position, String number){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
        this.timeNotPlayed = timeNotPlayed;
        this.timePlayed = timePlayed;
    }

    public float getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(float timePlayed) {
        this.timePlayed = timePlayed;
    }

    public float getTimeNotPlayed() {

        return timeNotPlayed;
    }

    public void setTimeNotPlayed(float timeNotPlayed) {
        this.timeNotPlayed = timeNotPlayed;
    }

    public String getPosition() {

        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

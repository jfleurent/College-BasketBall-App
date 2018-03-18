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
    private String birthDate;
    private String birthPlace;
    private String height;
    private String weight;
    private String totalAssists;
    private String assistsPerGame;
    private String turnoverToAssist;
    private String blockAttempts;
    private String blockAttemptsPerGame;
    private String sucessfulBlocks;
    private String sucessfulBlocksPerGame;
    private String defensiveRebounds;
    private String defensiveReboundsPerGame;
    private String freeThrowsAttempts;
    private String freeThrowsAttemptsPerGame;
    private String freeThrowPercentage;
    private String sucessfulfreeThrow;
    private String sucessfulFreeThrowPerGame;
    private String totalRebounds;
    private String reboundsPerGame;
    private String threePointAttempts;
    private String threePointAttemptsPerGame;
    private String threePointPercentage;
    private String sucessfulThreePoint;
    private String sucessfulThreePointPerGame;
    private String trueShotAttempts;
    private String trueShotAttemptsPerGame;
    private String trueShotPercentage;
    private String turnovers;
    private String turnoverPerGame;
    private String twoPointAttempts;
    private String twoPointAttemptsPerGame;
    private String twoPointAttemptsPercentage;
    private String sucessfulTwoPoint;
    private String sucessfulTwoPointPerGame;
    private String experience;


    public Player(String firstName, String lastName,
                  String position, String number, String experience){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number =number;
        this.experience = experience;

    }


    public Player(float timePlayed, float timeNotPlayed, String firstName, String lastName,
                  String position, String number,String birthDate,String birthPlace,
                  String height,String weight,String totalAssists,String assistsPerGame,
                  String turnoverToAssist, String blockAttempts,String blockAttemptsPerGame,
                  String sucessfulBlocks, String sucessfulBlocksPerGame, String defensiveRebounds,
                  String defensiveReboundsPerGame, String freeThrowsAttempts,
                  String freeThrowsAttemptsPerGame,String freeThrowPercentage, String sucessfulfreeThrow,
                  String sucessfulFreeThrowPerGame,String totalRebounds, String reboundsPerGame,
                  String threePointAttempts, String threePointAttemptsPerGame,String threePointPercentage,
                  String sucessfulThreePoint,String sucessfulThreePointPerGame, String trueShotAttempts,
                  String trueShotAttemptsPerGame, String trueShotPercentage,String turnovers,
                  String turnoverPerGame, String twoPointAttempts, String twoPointAttemptsPercentage,
                  String twoPointAttemptsPerGame, String sucessfulTwoPoint, String sucessfulTwoPointPerGame){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
        this.timeNotPlayed = timeNotPlayed;
        this.timePlayed = timePlayed;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.height = height;
        this.weight = weight;
        this.totalAssists = totalAssists;
        this.assistsPerGame = assistsPerGame;
        this.turnoverToAssist = turnoverToAssist;
        this.blockAttempts = blockAttempts;
        this.blockAttemptsPerGame = blockAttemptsPerGame;
        this.sucessfulBlocks = sucessfulBlocks;
        this.sucessfulBlocksPerGame = sucessfulBlocksPerGame;
        this.defensiveRebounds = defensiveRebounds;
        this.defensiveReboundsPerGame = defensiveReboundsPerGame;
        this.freeThrowsAttempts = freeThrowsAttempts;
        this.defensiveReboundsPerGame = freeThrowsAttemptsPerGame;
        this.freeThrowPercentage = freeThrowPercentage;
        this.sucessfulfreeThrow = sucessfulfreeThrow;
        this.sucessfulFreeThrowPerGame = sucessfulFreeThrowPerGame;
        this.totalRebounds = totalRebounds;
        this.reboundsPerGame = reboundsPerGame;
        this.threePointAttempts = threePointAttempts;
        this.threePointAttemptsPerGame = threePointAttemptsPerGame;
        this.threePointPercentage = threePointPercentage;
        this.sucessfulThreePoint = sucessfulThreePoint;
        this.sucessfulThreePointPerGame = sucessfulThreePointPerGame;
        this.trueShotAttempts = trueShotAttempts;
        this.trueShotAttemptsPerGame = trueShotAttemptsPerGame;
        this.trueShotPercentage = trueShotPercentage;
        this.turnovers =  turnovers;
        this.turnoverPerGame = turnoverPerGame;
        this.twoPointAttempts = twoPointAttempts;
        this.twoPointAttemptsPercentage = twoPointAttemptsPercentage;
        this.twoPointAttemptsPerGame = twoPointAttemptsPerGame;
        this.sucessfulTwoPoint = sucessfulTwoPoint;
        this.sucessfulTwoPointPerGame = sucessfulTwoPointPerGame;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public String getTwoPointAttempts() {
        return twoPointAttempts;
    }

    public String getTwoPointAttemptsPercentage() {
        return twoPointAttemptsPercentage;
    }

    public void setTwoPointAttemptsPercentage(String twoPointAttemptsPercentage) {
        this.twoPointAttemptsPercentage = twoPointAttemptsPercentage;
    }

    public void setTwoPointAttempts(String twoPointAttempts) {
        this.twoPointAttempts = twoPointAttempts;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTwoPointAttemptsPerGame() {

        return twoPointAttemptsPerGame;
    }

    public void setTwoPointAttemptsPerGame(String twoPointAttemptsPerGame) {
        this.twoPointAttemptsPerGame = twoPointAttemptsPerGame;
    }

    public String getTurnoverToAssist() {

        return turnoverToAssist;
    }

    public void setTurnoverToAssist(String turnoverToAssist) {
        this.turnoverToAssist = turnoverToAssist;
    }

    public String getTurnovers() {

        return turnovers;
    }

    public void setTurnovers(String turnovers) {
        this.turnovers = turnovers;
    }

    public String getTurnoverPerGame() {

        return turnoverPerGame;
    }

    public void setTurnoverPerGame(String turnoverPerGame) {
        this.turnoverPerGame = turnoverPerGame;
    }

    public String getTrueShotPercentage() {

        return trueShotPercentage;
    }

    public void setTrueShotPercentage(String trueShotPercentage) {
        this.trueShotPercentage = trueShotPercentage;
    }

    public String getTrueShotAttemptsPerGame() {

        return trueShotAttemptsPerGame;
    }

    public void setTrueShotAttemptsPerGame(String trueShotAttemptsPerGame) {
        this.trueShotAttemptsPerGame = trueShotAttemptsPerGame;
    }

    public String getTrueShotAttempts() {

        return trueShotAttempts;
    }

    public void setTrueShotAttempts(String trueShotAttempts) {
        this.trueShotAttempts = trueShotAttempts;
    }

    public String getTotalRebounds() {

        return totalRebounds;
    }

    public void setTotalRebounds(String totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public String getTotalAssists() {

        return totalAssists;
    }

    public void setTotalAssists(String totalAssists) {
        this.totalAssists = totalAssists;
    }

    public String getThreePointPercentage() {

        return threePointPercentage;
    }

    public void setThreePointPercentage(String threePointPercentage) {
        this.threePointPercentage = threePointPercentage;
    }

    public String getThreePointAttemptsPerGame() {

        return threePointAttemptsPerGame;
    }

    public void setThreePointAttemptsPerGame(String threePointAttemptsPerGame) {
        this.threePointAttemptsPerGame = threePointAttemptsPerGame;
    }

    public String getThreePointAttempts() {

        return threePointAttempts;
    }

    public void setThreePointAttempts(String threePointAttempts) {
        this.threePointAttempts = threePointAttempts;
    }

    public String getSucessfulTwoPointPerGame() {

        return sucessfulTwoPointPerGame;
    }

    public void setSucessfulTwoPointPerGame(String sucessfulTwoPointPerGame) {
        this.sucessfulTwoPointPerGame = sucessfulTwoPointPerGame;
    }

    public String getSucessfulTwoPoint() {

        return sucessfulTwoPoint;
    }

    public void setSucessfulTwoPoint(String sucessfulTwoPoint) {
        this.sucessfulTwoPoint = sucessfulTwoPoint;
    }

    public String getSucessfulThreePointPerGame() {

        return sucessfulThreePointPerGame;
    }

    public void setSucessfulThreePointPerGame(String sucessfulThreePointPerGame) {
        this.sucessfulThreePointPerGame = sucessfulThreePointPerGame;
    }

    public String getSucessfulThreePoint() {

        return sucessfulThreePoint;
    }

    public void setSucessfulThreePoint(String sucessfulThreePoint) {
        this.sucessfulThreePoint = sucessfulThreePoint;
    }

    public String getSucessfulFreeThrowPerGame() {

        return sucessfulFreeThrowPerGame;
    }

    public void setSucessfulFreeThrowPerGame(String sucessfulFreeThrowPerGame) {
        this.sucessfulFreeThrowPerGame = sucessfulFreeThrowPerGame;
    }

    public String getSucessfulfreeThrow() {

        return sucessfulfreeThrow;
    }

    public void setSucessfulfreeThrow(String sucessfulfreeThrow) {
        this.sucessfulfreeThrow = sucessfulfreeThrow;
    }

    public String getSucessfulBlocksPerGame() {

        return sucessfulBlocksPerGame;
    }

    public void setSucessfulBlocksPerGame(String sucessfulBlocksPerGame) {
        this.sucessfulBlocksPerGame = sucessfulBlocksPerGame;
    }

    public String getSucessfulBlocks() {

        return sucessfulBlocks;
    }

    public void setSucessfulBlocks(String sucessfulBlocks) {
        this.sucessfulBlocks = sucessfulBlocks;
    }

    public String getReboundsPerGame() {

        return reboundsPerGame;
    }

    public void setReboundsPerGame(String reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public String getHeight() {

        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFreeThrowsAttemptsPerGame() {

        return freeThrowsAttemptsPerGame;
    }

    public void setFreeThrowsAttemptsPerGame(String freeThrowsAttemptsPerGame) {
        this.freeThrowsAttemptsPerGame = freeThrowsAttemptsPerGame;
    }

    public String getFreeThrowsAttempts() {

        return freeThrowsAttempts;
    }

    public void setFreeThrowsAttempts(String freeThrowsAttempts) {
        this.freeThrowsAttempts = freeThrowsAttempts;
    }

    public String getFreeThrowPercentage() {

        return freeThrowPercentage;
    }

    public void setFreeThrowPercentage(String freeThrowPercentage) {
        this.freeThrowPercentage = freeThrowPercentage;
    }

    public String getDefensiveReboundsPerGame() {

        return defensiveReboundsPerGame;
    }

    public void setDefensiveReboundsPerGame(String defensiveReboundsPerGame) {
        defensiveReboundsPerGame = defensiveReboundsPerGame;
    }

    public String getDefensiveRebounds() {

        return defensiveRebounds;
    }

    public void setDefensiveRebounds(String defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public String getBlockAttemptsPerGame() {

        return blockAttemptsPerGame;
    }

    public void setBlockAttemptsPerGame(String blockAttemptsPerGame) {
        this.blockAttemptsPerGame = blockAttemptsPerGame;
    }

    public String getBlockAttempts() {

        return blockAttempts;
    }

    public void setBlockAttempts(String blockAttempts) {
        this.blockAttempts = blockAttempts;
    }

    public String getBirthPlace() {

        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAssistsPerGame() {

        return assistsPerGame;
    }

    public void setAssistsPerGame(String assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }
}


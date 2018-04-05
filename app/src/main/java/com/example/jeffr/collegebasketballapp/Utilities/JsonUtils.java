/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jeffr.collegebasketballapp.Utilities;

import android.content.Context;

import com.example.jeffr.collegebasketballapp.DataObjects.Game;
import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.jeffr.collegebasketballapp.Utilities.NetworkUtils.buildGameListUrl;

public final class JsonUtils {

    public static List<Team> getTeamsFromJson(Context context, String teamJsonStr)
            throws JSONException {

        List<Team> parsedTeamListData = new ArrayList<>();

        JSONObject teamJson = new JSONObject(teamJsonStr);

        JSONArray conferences = teamJson.getJSONArray("conferences");
        List<String> uniqueTeamList = new ArrayList<>();
        for(int i = 0; i < conferences.length(); i++){
           for(int j = 0;j < conferences.getJSONObject(i).getJSONArray("teams").length();j++){
               JSONObject team = conferences.getJSONObject(i).getJSONArray("teams").getJSONObject(j);
                if(!uniqueTeamList.contains(team.getString("id"))){
                    parsedTeamListData.add(new Team(team.getInt("wins"),team.getInt("losses")
                            ,team.getString("market"),team.getString("name"),null,team.getString("id"),new ArrayList<Game>()));
                    uniqueTeamList.add(team.getString("id"));
                }
           }
        }

        return parsedTeamListData;
    }

    public static List<Player> getPlayersFromJson(Context context, String teamPlayersJsonStr)
            throws JSONException {

        List<Player> parsedPlayerListData = new ArrayList<>();

        JSONObject playerJson = new JSONObject(teamPlayersJsonStr);

        JSONArray players = playerJson.getJSONArray("players");
        for(int i = 0; i < players.length(); i++){
            JSONObject player = players.getJSONObject(i);
            try {
                parsedPlayerListData.add(new Player(player.getString("first_name"), player.getString("last_name"),
                        player.getString("position"), player.getString("jersey_number"),
                        player.getString("experience"), player.getString("id")));
            }catch (Exception e){
                parsedPlayerListData.add(new Player(player.getString("first_name"), player.getString("last_name"),
                        player.getString("position"), "N/A",
                        player.getString("experience"), player.getString("id")));
            }
        }

        return parsedPlayerListData;
    }

    public static List<Game> getGamesFromJson(Context context, boolean male, int day, String year)
            throws JSONException,IOException {

        List<Game> parsedGameListData = new ArrayList<>();

        for(int i = 0; i < 1; i++){
           String teamPlayersJsonStr = NetworkUtils.getResponseFromHttpUrl(buildGameListUrl(male,day+i,year));
            JSONObject playerJson = new JSONObject(teamPlayersJsonStr);

            JSONArray games = playerJson.getJSONArray("games");
            for(int j = 0; j < games.length(); j++){
                JSONObject game = games.getJSONObject(j);
                String homeName = game.getJSONObject("home").getString("name");
                String homeId = game.getJSONObject("home").getString("id");
                String awayName = game.getJSONObject("away").getString("name");
                int homeScore = 0;
                int awayScore = 0;

                try{
                    homeScore = game.getInt("home_points");
                }

                catch (Exception e){

                }

                try{
                    awayScore = game.getInt("away_points");
                }
                catch (Exception e){

                }

                parsedGameListData.add(new Game(homeId,homeName,awayName,homeScore,awayScore));
            }
        }
        return parsedGameListData;
    }

    public static Player getPlayerFromJson(Context context, String teamJsonStr, int year, String teamId)
            throws JSONException {

        Player parsedPlayerData = null;

        JSONObject playerJson = new JSONObject(teamJsonStr);

        JSONArray season = playerJson.getJSONArray("seasons");
        for(int i = 0; i < season.length(); i++) {
            if (season.getJSONObject(i).getInt("year") == year) {
                for (int j = 0; j < season.getJSONObject(i).getJSONArray("teams").length(); j++) {
                    if (season.getJSONObject(i).getJSONArray("teams").
                            getJSONObject(j).getString("id").equals(teamId)) {
                        JSONObject team = season.getJSONObject(i).getJSONArray("teams").
                                getJSONObject(j);
                        JSONObject total = team.getJSONObject("total");
                        JSONObject average = team.getJSONObject("average");
                        String weight;
                        String number;

                        try {
                            number = playerJson.getString("jersey_number");
                        } catch (Exception e) {
                            number = "N/A";
                        }

                        try {
                            weight = String.valueOf(playerJson.getInt("weight"));
                        }
                        catch (Exception e2) {
                            weight = "N/A";
                        }
                            parsedPlayerData = new Player(total.getInt("games_started"),
                                    total.getInt("games_played") - total.getInt("games_started"),
                                    playerJson.getString("first_name"), playerJson.getString("last_name"),
                                    playerJson.getString("position"), number,
                                    playerJson.getString("birth_place"), String.valueOf(playerJson.getInt("height")),
                                    weight, String.valueOf(total.getInt("assists")),
                                    String.valueOf(average.getDouble("assists")), String.valueOf(total.getDouble("assists_turnover_ratio")),
                                    String.valueOf(total.getInt("blocked_att")), String.valueOf(average.getDouble("blocked_att")),
                                    String.valueOf(total.getInt("blocks")), String.valueOf(average.getDouble("blocks")),
                                    String.valueOf(total.getInt("defensive_rebounds")), String.valueOf(average.getDouble("def_rebounds")),
                                    String.valueOf(total.getInt("free_throws_att")), String.valueOf(average.getDouble("free_throws_att")),
                                    String.valueOf(total.getDouble("free_throws_pct")), String.valueOf(total.getInt("free_throws_made")),
                                    String.valueOf(average.getDouble("free_throws_made")), String.valueOf(total.getInt("offensive_rebounds")),
                                    String.valueOf(average.getDouble("off_rebounds")), String.valueOf(total.getInt("three_points_att")),
                                    String.valueOf(average.getDouble("three_points_att")), String.valueOf(total.getDouble("three_points_pct")),
                                    String.valueOf(total.getInt("three_points_made")), String.valueOf(average.getDouble("three_points_att")),
                                    String.valueOf(total.getDouble("true_shooting_att")), String.valueOf(average.getDouble("true_shooting_att")),
                                    String.valueOf(total.getDouble("true_shooting_pct")), String.valueOf(total.getInt("turnovers")),
                                    String.valueOf(average.getDouble("turnovers")), String.valueOf(total.getInt("two_points_att")),
                                    String.valueOf(total.getDouble("two_points_pct")), String.valueOf(average.getDouble("two_points_att")),
                                    String.valueOf(total.getInt("two_points_made")), String.valueOf(average.getDouble("two_points_made")));
                        }

                    }
                }

            }
        return parsedPlayerData;
    }

}
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
package com.example.jeffr.collegebasketballapp;

import android.content.ContentValues;
import android.content.Context;

import com.example.jeffr.collegebasketballapp.DataObjects.Player;
import com.example.jeffr.collegebasketballapp.DataObjects.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public final class TeamListJsonUtils {

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
                            ,team.getString("market"),team.getString("name"),null,team.getString("id")));
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
            parsedPlayerListData.add(new Player(player.getString("first_name"),player.getString("last_name"),
                    player.getString("primary_position"),player.getString("jersey_number"),player.getString("experience")));
        }

        return parsedPlayerListData;
    }

    public static Player getPlayerFromJson(Context context, String teamJsonStr)
            throws JSONException {

        Player parsedPlayerData = null;

        JSONObject playerJson = new JSONObject(teamJsonStr);

        JSONArray conferences = playerJson.getJSONArray("conferences");
        for(int i = 0; i < conferences.length(); i++){
            for(int j = 0;j < conferences.getJSONObject(i).getJSONArray("teams").length();j++){
                JSONObject team = conferences.getJSONObject(i).getJSONArray("teams").getJSONObject(j);
            }
        }

        return parsedPlayerData;
    }

}
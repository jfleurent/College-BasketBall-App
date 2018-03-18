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

import com.example.jeffr.collegebasketballapp.DataObjects.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class TeamListJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param teamJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static List<Team> getTeamsFromJson(Context context, String teamJsonStr)
            throws JSONException {

        final String OWM_MESSAGE_CODE = "cod";

        /* String array to hold each day's weather String */
        List<Team> parsedTeamListData = new ArrayList<>();

        JSONObject teamJson = new JSONObject(teamJsonStr);

//        /* Is there an error? */
//        if (teamJson.has(OWM_MESSAGE_CODE)) {
//            int errorCode = teamJson.getInt(OWM_MESSAGE_CODE);
//
//            switch (errorCode) {
//                case HttpURLConnection.HTTP_OK:
//                    break;
//                case HttpURLConnection.HTTP_NOT_FOUND:
//                    /* Location invalid */
//                    return null;
//                default:
//                    /* Server probably down */
//                    return null;
//            }
//        }

        JSONArray conferences = teamJson.getJSONArray("conferences");
        List<String> uniqueTeamList = new ArrayList<>();
        for(int i = 0; i < conferences.length(); i++){
           for(int j = 0;j < conferences.getJSONObject(i).getJSONArray("teams").length();j++){
               JSONObject team = conferences.getJSONObject(i).getJSONArray("teams").getJSONObject(j);
                if(!uniqueTeamList.contains(team.getString("id"))){
                    parsedTeamListData.add(new Team(team.getInt("wins"),team.getInt("losses")
                            ,team.getString("market"),team.getString("name"),null));
                    uniqueTeamList.add(team.getString("id"));
                }

           }
        }




        return parsedTeamListData;
    }

    /**
     * Parse the JSON and convert it into ContentValues that can be inserted into our database.
     *
     * @param context         An application context, such as a service or activity context.
     * @param teamJsonStr The JSON to parse into ContentValues.
     *
     * @return An array of ContentValues parsed from the JSON.
     */
    public static ContentValues[] getFullWeatherDataFromJson(Context context, String teamJsonStr) {
        /** This will be implemented in a future lesson **/
        return null;
    }
}
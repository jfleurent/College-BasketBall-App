package com.example.jeffr.collegebasketballapp.Utilities;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //For men college basketball
    private static final String DYNAMIC_NCAAMB_URL =
            "https://api.sportradar.us/ncaamb";

    //For women college basketball
    private static final String DYNAMIC_NCAAWB_URL =
            "https://api.sportradar.us/ncaawb";

    private static String TEAM_DATA_BASE_URL = DYNAMIC_NCAAMB_URL;

    private static final String format = "json";

    private static final String accessLevel = "trial";

    private static final String version = "v4";

    private static final String languageCode = "en";

    private static String apiKey = " ";


    public static URL buildPlayerUrl(boolean male, String player_id) {
        TEAM_DATA_BASE_URL = male ? DYNAMIC_NCAAMB_URL : DYNAMIC_NCAAWB_URL;
        apiKey = male ? "dsz6v6zq4n6h96sarcfpr5tt" : "pu277uqj6ne5wvjp5m7b9kpt";
        String query = TEAM_DATA_BASE_URL + "/"+ accessLevel +"/"+version + "/" + languageCode + "/players/" +
                player_id +"/profile." + format +"?api_key="+apiKey;

        URL url = null;
        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static URL buildTeamUrl(boolean male, String team_id) {
        TEAM_DATA_BASE_URL = male ? DYNAMIC_NCAAMB_URL : DYNAMIC_NCAAWB_URL;
        apiKey = male ? "dsz6v6zq4n6h96sarcfpr5tt" : "pu277uqj6ne5wvjp5m7b9kpt";
        String query = TEAM_DATA_BASE_URL + "/"+ accessLevel + "/"+version + "/" + languageCode + "/teams/" +
                team_id +"/profile." + format +"?api_key="+apiKey;

        URL url = null;
        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static URL buildTeamListUrl(boolean male,String year) {
        TEAM_DATA_BASE_URL = male ? DYNAMIC_NCAAMB_URL : DYNAMIC_NCAAWB_URL;
        apiKey = male ? "dsz6v6zq4n6h96sarcfpr5tt" : "pu277uqj6ne5wvjp5m7b9kpt";
        String query = TEAM_DATA_BASE_URL + "/"+ accessLevel + "/"+version + "/" + languageCode + "/seasons/" +
                year + "/REG/standings." + format +"?api_key="+apiKey;

        URL url = null;
        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static URL buildPlayerListUrl(boolean male,String teamId) {
        TEAM_DATA_BASE_URL = male ? DYNAMIC_NCAAMB_URL : DYNAMIC_NCAAWB_URL;
        apiKey = male ? "dsz6v6zq4n6h96sarcfpr5tt" : "pu277uqj6ne5wvjp5m7b9kpt";
        String query = TEAM_DATA_BASE_URL + "/"+ accessLevel + "/"+version + "/" + languageCode + "/teams/" +
                teamId + "/profile." + format +"?api_key="+apiKey;

        URL url = null;
        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /*https://api.sportradar.us/ncaamb/{access_level}/{version}/{language_code}/games/{year}/{month}/{day}/
    schedule.{format}?api_key={your_api_key}*/
    public static URL buildGameListUrl(boolean male,int day,String year) {
        TEAM_DATA_BASE_URL = male ? DYNAMIC_NCAAMB_URL : DYNAMIC_NCAAWB_URL;
        apiKey = male ? "dsz6v6zq4n6h96sarcfpr5tt" : "pu277uqj6ne5wvjp5m7b9kpt";
        String query = TEAM_DATA_BASE_URL + "/"+ accessLevel + "/"+version + "/" + languageCode + "/games/" +year+"/12/"+
                day + "/schedule." + format +"?api_key="+apiKey;

        URL url = null;
        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
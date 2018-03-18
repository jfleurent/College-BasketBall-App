package com.example.jeffr.collegebasketballapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //For men college basketball
    private static final String DYNAMIC_NCAAMB_URL =
            "https://api.sportradar.us/ncaamb";

    //For wommen college basketball
    //TODO might have to change if not right base
    private static final String DYNAMIC_NCAAWB_URL =
            "https://api.sportradar.us/ncaawb";

    private static String TEAM_DATA_BASE_URL = DYNAMIC_NCAAMB_URL;

    /*
     * NOTE: These values only effect responses from OpenWeatherMap, NOT from the fake weather
     * server. They are simply here to allow us to teach you how to build a URL if you were to use
     * a real API.If you want to connect your app to OpenWeatherMap's API, feel free to! However,
     * we are not going to show you how to do so in this course.
     */

    /* The format we want our API to return */
    private static final String format = "json";

    private static final String accessLevel = "trial";
    /* The units we want our API to return */

    private static final String version = "v4";

    private static final String languageCode = "en";

    private static String apiKey = " ";


    public static URL buildPlayerUrl(boolean male, String player_id) {
        TEAM_DATA_BASE_URL = male ? DYNAMIC_NCAAMB_URL : DYNAMIC_NCAAWB_URL;
        apiKey = male ? "4h8d6yn5wvnkjawh5engcyxq" : "n7wczq8gmnxxu9acw7sktyax";
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
        apiKey = male ? "4h8d6yn5wvnkjawh5engcyxq" : "n7wczq8gmnxxu9acw7sktyax";
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
        apiKey = male ? "4h8d6yn5wvnkjawh5engcyxq" : "n7wczq8gmnxxu9acw7sktyax";
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

    /**
     * Builds the URL used to talk to the weather server using latitude and longitude of a
     * location.
     *
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     * @return The Url to use to query the weather server.
     */
    public static URL buildUrl(Double lat, Double lon) {
        /** This will be implemented in a future lesson **/
        return null;
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
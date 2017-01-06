package com.example.athensgift.wheatherapp.Model.WeatherModels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Location {
    public String city;
    public String country;
    public String region;

    public static Location getLocationFromJson(JSONObject json){
        final Location location = new Location();

        try {
            location.city = json.getString("city");
            location.country = json.getString("country");
            location.region = json.getString("region");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return location;
    }
}

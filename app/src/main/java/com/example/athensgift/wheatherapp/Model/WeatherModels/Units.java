package com.example.athensgift.wheatherapp.Model.WeatherModels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Units {
    public String distance;
    public String pressure;
    public String speed;
    public String temperature;

    public static Units getUnitsFromJson(JSONObject json){
        final Units units = new Units();

        try {
            units.distance = json.getString("distance");
            units.pressure = json.getString("pressure");
            units.speed = json.getString("speed");
            units.temperature = json.getString("temperature");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return units;
    }
}

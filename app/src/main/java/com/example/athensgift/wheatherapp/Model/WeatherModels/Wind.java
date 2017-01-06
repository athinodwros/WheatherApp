package com.example.athensgift.wheatherapp.Model.WeatherModels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Wind {
    public Double chill;
    public Double direction;
    public Double speed;

    public static Wind getWindFromJson(JSONObject json){
        final Wind wind = new Wind();

        try {
            wind.chill = json.getDouble("chill");
            wind.direction = json.getDouble("direction");
            wind.speed = json.getDouble("speed");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wind;
    }
}


package com.example.athensgift.wheatherapp.Model.WeatherModels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Astronomy {
    public String sunrise;
    public String sunset;

    public static Astronomy getAstronomyFromJson(JSONObject json){
        final Astronomy astronomy = new Astronomy();

        try {
            astronomy.sunrise = json.getString("sunrise");
            astronomy.sunset = json.getString("sunset");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return astronomy;
    }
}

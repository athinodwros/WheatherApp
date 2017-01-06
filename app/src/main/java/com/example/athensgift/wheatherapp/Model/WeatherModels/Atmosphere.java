package com.example.athensgift.wheatherapp.Model.WeatherModels;

import android.content.Context;

import com.example.athensgift.wheatherapp.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Atmosphere {
    public Double humidity;
    public Double pressure;
    public Double rising;
    public Double visibility;

    public static Atmosphere getAtmosphereFromJson(JSONObject json){
        final Atmosphere atmosphere = new Atmosphere();

        try {
            atmosphere.humidity = json.getDouble("humidity");
            atmosphere.pressure = json.getDouble("pressure");
            atmosphere.rising = json.getDouble("rising");
            atmosphere.visibility = json.getDouble("visibility");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return atmosphere;
    }

    public String toString(Context ctx) {
        return ctx.getString(R.string.atmosphere_label)+ctx.getString(R.string.humidity)+": "+humidity.toString()+", "+ctx.getString(R.string.pressure)+": "+pressure.toString()+", "+ctx.getString(R.string.rising)+": "+rising.toString()+", "+ctx.getString(R.string.visibility)+": "+visibility.toString();
    }
}
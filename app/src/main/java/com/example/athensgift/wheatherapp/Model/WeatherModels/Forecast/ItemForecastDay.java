package com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class ItemForecastDay {
    public String code;
    public String date;
    public String day;
    public Double high;
    public Double low;
    public String text;

    public static ItemForecastDay getItemForecastDayFromJson(JSONObject json){
        final ItemForecastDay itemForecastDay = new ItemForecastDay();

        try {
            itemForecastDay.code = json.getString("code");
            itemForecastDay.date = json.getString("date");
            itemForecastDay.day = json.getString("day");
            itemForecastDay.high = json.getDouble("high");
            itemForecastDay.low = json.getDouble("low");
            itemForecastDay.text = json.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemForecastDay;
    }
}
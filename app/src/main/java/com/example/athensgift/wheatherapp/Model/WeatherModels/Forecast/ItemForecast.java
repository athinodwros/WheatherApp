package com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class ItemForecast {
    public ArrayList<ItemForecastDay> itemForecastDays;

    public static ItemForecast getItemForecastFromJson(JSONArray json){
        final ItemForecast itemForecast = new ItemForecast();

        try {
            itemForecast.itemForecastDays = new ArrayList<ItemForecastDay>();

            for(int i = 0; i < json.length(); i++){
                itemForecast.itemForecastDays.add(ItemForecastDay.getItemForecastDayFromJson(json.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemForecast;
    }
}

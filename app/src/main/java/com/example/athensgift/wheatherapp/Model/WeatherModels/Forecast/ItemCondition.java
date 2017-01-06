package com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class ItemCondition {
    public String code;
    public String date;
    public Double temp;
    public String text;

    public static ItemCondition getItemConditionFromJson(JSONObject json){
        final ItemCondition itemCondition = new ItemCondition();

        try {
            itemCondition.code = json.getString("code");
            itemCondition.date = json.getString("date");
            itemCondition.temp = json.getDouble("temp");
            itemCondition.text = json.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemCondition;
    }
}
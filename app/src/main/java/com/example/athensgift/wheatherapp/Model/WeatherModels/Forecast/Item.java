package com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Item {
    public String title;
    public double lat;
    public double lon;
    public String link;
    public String pubDate;
    public ItemCondition itemCondition;
    public ItemForecast itemForecast;
    public String description;

    public static Item getItemFromJson(JSONObject json){
        final Item item = new Item();

        try {
            item.title = json.getString("title");
            item.lat = json.getDouble("lat");
            item.lon = json.getDouble("long");
            item.link = json.getString("link");
            item.pubDate = json.getString("pubDate");
            item.itemCondition = ItemCondition.getItemConditionFromJson(json.getJSONObject("condition"));
            item.itemForecast = ItemForecast.getItemForecastFromJson(json.getJSONArray("forecast"));
            item.description = json.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }
}





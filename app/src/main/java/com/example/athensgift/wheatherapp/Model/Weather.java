package com.example.athensgift.wheatherapp.Model;

import com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast.Item;
import com.example.athensgift.wheatherapp.Model.WeatherModels.*;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Fafoutis Athinodoros on 05/01/2017.
 */

public class Weather {
    public Units units;
    public String title;
    public String link;
    public String description;
    public String language;
    public String lastBuildDate;
    public String ttl;
    public Location location;
    public Wind wind;
    public Atmosphere atmosphere;
    public Astronomy astronomy;
    public Image image;
    public Item item;

    public static Weather getWeatherFromJson(String jsonString){
        final Weather weather = new Weather();

        try {
            // Load data
            JSONObject json = new JSONObject(jsonString);
            JSONObject results = json.optJSONObject("query").optJSONObject("results");

            if (results==null) {return null;}

            JSONObject channel = results.optJSONObject("channel");

            weather.units = Units.getUnitsFromJson(channel.optJSONObject("units"));
            weather.title = channel.getString("title");
            weather.link = channel.getString("link");
            weather.description = channel.getString("description");
            weather.language = channel.getString("language");
            weather.lastBuildDate = channel.getString("lastBuildDate");
            weather.ttl = channel.getString("ttl");
            weather.location = Location.getLocationFromJson(channel.optJSONObject("location"));
            weather.wind = Wind.getWindFromJson(channel.optJSONObject("wind"));
            weather.atmosphere = Atmosphere.getAtmosphereFromJson(channel.optJSONObject("atmosphere"));
            weather.astronomy = Astronomy.getAstronomyFromJson(channel.optJSONObject("astronomy"));
            weather.image = Image.getImageFromJson(channel.optJSONObject("image"));
            weather.item = Item.getItemFromJson(channel.optJSONObject("item"));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return weather;
    }
}









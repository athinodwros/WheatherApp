package com.example.athensgift.wheatherapp.Model.WeatherModels;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Image {
    public String title;
    public double width;
    public double height;
    public String link;
    public String url;

    public static Image getImageFromJson(JSONObject json){
        final Image image = new Image();

        try {
            image.title = json.getString("title");
            image.width = json.getDouble("width");
            image.height = json.getDouble("height");
            image.link = json.getString("link");
            image.url = json.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return image;
    }
}
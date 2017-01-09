package com.example.athensgift.wheatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashMap;

/**
 * Created by Fafoutis Athinodoros on 06/01/2017.
 */

public class Global {
    public static final HashMap<String, String> wheatherImages = new HashMap<String, String>() {{
        put("Scattered Thunderstorms", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-storm-night-icon.png");
        put("Mostly Cloudy", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-many-clouds-icon.png");
        put("Partly Cloudy", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-clouds-icon.png");
        put("Scattered Showers", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-icon.png");
        put("Rain", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-scattered-day-icon.png");
        put("Showers", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-icon.png");
        put("Cloudy", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-many-clouds-icon.png");
        put("Rain And Snow", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-hail-icon.png");
        put("Mostly Sunny", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-clear-icon.png");
        put("Sunny", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-clear-icon.png");
        put("Snow Showers", "http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-snow-scattered-night-icon.png");
    }};
}

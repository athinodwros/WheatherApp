package com.example.athensgift.wheatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.athensgift.wheatherapp.Adapters.WeatherDayAdapter;
import com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast.ItemForecastDay;

import java.util.ArrayList;


public class WeatherForecastActivity extends AppCompatActivity {

    private ListView listView;
    private WeatherDayAdapter adapter;
    private ArrayList<ItemForecastDay> itemForecastDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("itemForecastDays")) {
                itemForecastDays = (ArrayList<ItemForecastDay>) extras.getSerializable("itemForecastDays");

                listView = (ListView) findViewById(R.id.weather_forecast_listView);
                adapter = new WeatherDayAdapter(WeatherForecastActivity.this, itemForecastDays);
                listView.setAdapter(adapter);
            }
        }
    }
}


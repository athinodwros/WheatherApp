package com.example.athensgift.wheatherapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.athensgift.wheatherapp.Adapters.WeatherDayAdapter;
import com.example.athensgift.wheatherapp.Model.Weather;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecastActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ListView listView;
    private ProgressBar progressBar;
    private WeatherDayAdapter adapter;
    LocationManager mLocationManager;

    void showSimpleAlert(String title,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(WeatherForecastActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        setProgressBarIndeterminateVisibility(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listView = (ListView) findViewById(R.id.weather_forecast_listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 11.0f));
            new getWeatherFromUrl().execute("https://simple-weather.p.mashape.com/weatherdata?lat=" + location.getLatitude() + "&lng=" + location.getLongitude());
        } else {
            showSimpleAlert(getResources().getString(R.string.alert_title_null_location),getResources().getString(R.string.alert_message_null_location));
        }
    }


    //region asynchronous http call
    private class getWeatherFromUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-Mashape-Key", "xNIMmWoiOImshYwi8ZRALGfcAEzSp1yycupjsnW2RncdhDEyVP");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();

                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String content = "", line;
                while ((line = rd.readLine()) != null) {
                    content += line + "\n";
                }
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            return "weather forecast failed";
        }

        protected void onPostExecute(String result) {
            Weather weather = Weather.getWeatherFromJson(result);

            adapter = new WeatherDayAdapter(WeatherForecastActivity.this, weather.item.itemForecast.itemForecastDays);
            listView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
        protected void onPreExecute() {

        }
        protected void onProgressUpdate(Void... values) {}
    }
    //endregion
}


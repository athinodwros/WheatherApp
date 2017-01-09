package com.example.athensgift.wheatherapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.athensgift.wheatherapp.Model.Weather;
import com.example.athensgift.wheatherapp.Providers.SingleShotLocationProvider;
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

import it.sephiroth.android.library.picasso.Picasso;

public class WeatherActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ProgressBar progressBar;
    private TextView weatherTitle;
    private TextView errorTextView;
    private TextView weathersWind;
    private TextView weathersAtmosphere;
    private TextView weathersAstronomy;
    private ImageView weatherDayListThumbnail;
    private TextView weathersDetails;
    private TextView weathersNowTitle;
    private TextView weathersNowSubtitle;
    private TextView weathersNowLabel;
    private Boolean areCustomCoordinates;
    private Button dailyWeatherForecastButton;
    private Double lat;
    private Double lon;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        errorTextView = (TextView) findViewById(R.id.listErrorMessage);
        weatherTitle = (TextView) findViewById(R.id.weathers_title);
        weathersWind = (TextView) findViewById(R.id.weathers_wind);
        weathersAtmosphere = (TextView) findViewById(R.id.weathers_atmosphere);
        weathersAstronomy = (TextView) findViewById(R.id.weathers_astronomy);
        weatherDayListThumbnail = (ImageView) findViewById(R.id.weather_day_list_thumbnail);
        weathersDetails = (TextView) findViewById(R.id.weather_day_list_detail);
        weathersNowTitle = (TextView) findViewById(R.id.weather_day_list_title);
        weathersNowSubtitle = (TextView) findViewById(R.id.weather_day_list_subtitle);
        weathersNowLabel = (TextView) findViewById(R.id.weathers_now_label);
        dailyWeatherForecastButton = (Button) findViewById(R.id.daily_weather_forecast_button);

        if (!isOnline()){
            errorTextView.setText(R.string.internet_connection_error);
            errorTextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        weather = new Weather();
        areCustomCoordinates = false;

        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("lat") && extras.containsKey("lon")) {
                areCustomCoordinates = true;

                lat = extras.getDouble("lat");
                lon = extras.getDouble("lon");

                if (lat!=null && lon!=null) {
                    new WeatherActivity.getWeatherFromUrl().execute("https://simple-weather.p.mashape.com/weatherdata?lat=" + lat + "&lng=" + lon);
                }
            }
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (areCustomCoordinates) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 7.0f));
        }
        else{
            SingleShotLocationProvider.requestSingleUpdate(this,
                    new SingleShotLocationProvider.LocationCallback() {
                        @Override public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                            if (location != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.latitude, location.longitude), 7.0f));
                                new WeatherActivity.getWeatherFromUrl().execute("https://simple-weather.p.mashape.com/weatherdata?lat=" + location.latitude + "&lng=" + location.longitude);
                            } else {
                                showSimpleAlert(getResources().getString(R.string.alert_title_null_location),getResources().getString(R.string.alert_message_null_location));
                            }
                        }
                    });
        }
    }

    public void showDailyForecast(View view) {
        Intent intent = new Intent(this, WeatherForecastActivity.class);
        intent.putExtra("itemForecastDays", weather.item.itemForecast.itemForecastDays);

        startActivity(intent);
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
            weather = Weather.getWeatherFromJson(result);

            if (weather!=null && weather.item != null){
                WeatherActivity.this.setTitle(weather.location.city+", "+weather.location.region+", "+weather.location.country);

                weatherTitle.setText(weather.item.title);
                weathersWind.setText(weather.wind.toString(WeatherActivity.this));
                weathersAtmosphere.setText(weather.atmosphere.toString(WeatherActivity.this));
                weathersAstronomy.setText(weather.astronomy.toString(WeatherActivity.this));
                weathersDetails.setText(weather.item.itemCondition.temp.toString());
                weathersNowTitle.setText(weather.item.itemCondition.date);
                weathersNowSubtitle.setText(weather.item.itemCondition.text);
                dailyWeatherForecastButton.setVisibility(View.VISIBLE);
                weathersNowLabel.setVisibility(View.VISIBLE);

                String imageUrl = Global.wheatherImages.get(weather.item.itemCondition.text);
                Picasso.with(WeatherActivity.this).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(weatherDayListThumbnail);
            }
            else {
                errorTextView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);
        }
        protected void onPreExecute() {

        }
        protected void onProgressUpdate(Void... values) {}
    }
    //endregion

    //region Helpers
    void showSimpleAlert(String title,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(WeatherActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    //endregion
}

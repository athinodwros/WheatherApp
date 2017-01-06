package com.example.athensgift.wheatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void goToWeatherActivity(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    public void goToWeatherActivityWithCoordinates(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);

        EditText latEditText = (EditText) findViewById(R.id.editLatText);
        EditText lonEditText = (EditText) findViewById(R.id.editLongText);

        String latStr = latEditText.getText().toString();
        String lonStr = lonEditText.getText().toString();
        Double lat = (latStr == null || latStr.isEmpty()) ? 1.0 : Double.parseDouble(latStr);
        Double lon = (lonStr == null || lonStr.isEmpty()) ? 1.0 : Double.parseDouble(lonStr);
        intent.putExtra("lat",lat);
        intent.putExtra("lon",lon);

        startActivity(intent);
    }
}

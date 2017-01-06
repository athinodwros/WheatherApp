package com.example.athensgift.wheatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast.ItemForecastDay;
import com.example.athensgift.wheatherapp.R;

import java.util.ArrayList;
import java.util.Random;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Fafoutis Athinodoros on 05/01/2017.
 */

public class WeatherDayAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ItemForecastDay> mDataSource;

    public WeatherDayAdapter(Context context, ArrayList<ItemForecastDay> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_weather_day, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.weather_day_list_title);
        TextView subTitleTextView = (TextView) rowView.findViewById(R.id.weather_day_list_subtitle);
        TextView details = (TextView) rowView.findViewById(R.id.weather_day_list_detail);
        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.weather_day_list_thumbnail);

        ItemForecastDay itemForecastDay = (ItemForecastDay) getItem(position);
        titleTextView.setText(itemForecastDay.day+" "+itemForecastDay.date);
        subTitleTextView.setText(itemForecastDay.text);
        details.setText(itemForecastDay.low+"-"+itemForecastDay.high);
        String[] images = {"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-clouds-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-clear-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-day-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-scattered-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-snow-scattered-day-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-clouds-night-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-many-clouds-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-scattered-day-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-snow-scattered-night-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-hail-icon.png"
                ,"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Status-weather-showers-icon.png"};

        Random r = new Random();
        int i = r.nextInt(11);

        Picasso.with(mContext).load(images[i]).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return rowView;
    }
}

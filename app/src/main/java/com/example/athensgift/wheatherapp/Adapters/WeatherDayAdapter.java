package com.example.athensgift.wheatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.athensgift.wheatherapp.Global;
import com.example.athensgift.wheatherapp.Model.WeatherModels.Forecast.ItemForecastDay;
import com.example.athensgift.wheatherapp.R;

import java.util.ArrayList;

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
        ViewHolder holder;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_weather_day, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.weather_day_list_title);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.weather_day_list_subtitle);
            holder.detailTextView = (TextView) convertView.findViewById(R.id.weather_day_list_detail);
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.weather_day_list_thumbnail);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        ItemForecastDay itemForecastDay = (ItemForecastDay) getItem(position);

        titleTextView.setText(itemForecastDay.day+" "+itemForecastDay.date);
        subtitleTextView.setText(itemForecastDay.text);
        detailTextView.setText(itemForecastDay.low+"-"+itemForecastDay.high);

        String imageUrl = Global.wheatherImages.get(itemForecastDay.text);
        Picasso.with(mContext).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }
}

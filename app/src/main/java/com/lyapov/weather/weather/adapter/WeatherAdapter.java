package com.lyapov.weather.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lyapov.weather.R;
import com.lyapov.weather.bases.BaseRecyclerAdapter;
import com.lyapov.weather.data.models.ForecastEntry;

import java.util.List;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherAdapter extends BaseRecyclerAdapter {

    private enum Type {
        Day, Hour
    }

    public WeatherAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        Object object = getItem(position);

        if (object instanceof List) {
            return Type.Day.ordinal();
        } else {
            return Type.Hour.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Type type = Type.values()[viewType];

        switch (type) {
            case Day:
                return new WeatherDayViewHolder(this, getView(R.layout.item_weather_day, parent));

            case Hour:
                return new WeatherHourViewHolder(this, getView(R.layout.item_weather_hour, parent));

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WeatherDayViewHolder) {
            setWeatherDayViewHolder((WeatherDayViewHolder) holder, position);
        } else if (holder instanceof WeatherHourViewHolder) {
            setWeatherHourViewHolder((WeatherHourViewHolder) holder, position);
        }
    }

    private void setWeatherDayViewHolder(WeatherDayViewHolder viewHolder, int position) {
        List<ForecastEntry> forecastEntries = (List<ForecastEntry>) getItem(position);
        viewHolder.setData(forecastEntries);
    }

    private void setWeatherHourViewHolder(WeatherHourViewHolder viewHolder, int position) {
        ForecastEntry forecastEntry = (ForecastEntry) getItem(position);
        viewHolder.setData(forecastEntry);
    }
}

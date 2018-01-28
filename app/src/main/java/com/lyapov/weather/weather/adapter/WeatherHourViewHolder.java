package com.lyapov.weather.weather.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyapov.weather.R;
import com.lyapov.weather.bases.BaseRecyclerViewHolder;
import com.lyapov.weather.data.models.ForecastEntry;
import com.lyapov.weather.data.models.ForecastWeather;
import com.lyapov.weather.utils.DateUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherHourViewHolder extends BaseRecyclerViewHolder<ForecastEntry, WeatherAdapter> {

    @BindView(R.id.tv_time)
    public TextView mDateTextView;

    @BindView(R.id.iv_icon)
    public ImageView mIconImageView;

    @BindView(R.id.tv_description)
    public TextView mDescriptionTextView;

    public WeatherHourViewHolder(WeatherAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    public void setData(ForecastEntry data) {
        mDateTextView.setText(DateUtil.getHourAndMinutes(data.getDate()));
        mDescriptionTextView.setText(getContext().getString(R.string.activity_weather_weather_hour_item_temperature, data.getMain().getTemp()));

        String icon = null;
        if (data.hasWeather()) {
            ForecastWeather forecastWeather = data.getWeather().get(0);
            icon = getContext().getString(R.string.activity_weather_weather_hour_item_icon_path,
                    forecastWeather.getIcon());
        }

        Picasso.with(getContext())
                .load(icon)
                .into(mIconImageView);
    }
}

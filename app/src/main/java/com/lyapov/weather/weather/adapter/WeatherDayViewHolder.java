package com.lyapov.weather.weather.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyapov.weather.R;
import com.lyapov.weather.bases.BaseRecyclerViewHolder;
import com.lyapov.weather.data.models.ForecastEntry;
import com.lyapov.weather.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherDayViewHolder extends BaseRecyclerViewHolder<List<ForecastEntry>, WeatherAdapter> {

    @BindView(R.id.tv_date)
    public TextView mDateTextView;

    @BindView(R.id.tv_description)
    public TextView mDescriptionTextView;

    @BindView(R.id.iv_arrow)
    public ImageView mArrowImageView;

    @BindView(R.id.rv_weather_hours)
    public RecyclerView mHoursRecyclerView;

    private WeatherAdapter mAdapter;

    public WeatherDayViewHolder(WeatherAdapter adapter, View itemView) {
        super(adapter, itemView);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new WeatherAdapter(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        mHoursRecyclerView.setLayoutManager(layoutManager);
        mHoursRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setData(List<ForecastEntry> data) {
        long date = Long.MIN_VALUE;
        float tempMin = Float.MIN_VALUE;
        float tempMax = Float.MIN_VALUE;

        for (ForecastEntry forecastEntry : data) {
            date = date == Long.MIN_VALUE ?
                    forecastEntry.getDate() : Math.min(date, forecastEntry.getDate());

            tempMin = tempMin == Float.MIN_VALUE ? forecastEntry.getMain().getTempMin() :
                    Math.min(tempMin, forecastEntry.getMain().getTempMin());

            tempMax = tempMax == Float.MIN_VALUE ? forecastEntry.getMain().getTempMax() :
                    Math.max(tempMax, forecastEntry.getMain().getTempMax());
        }

        mDateTextView.setText(DateUtil.getDayOfWeek(date));

        mDescriptionTextView.setText(getContext().getString(
                R.string.activity_weather_weather_day_item_temperature, tempMin, tempMax));

        mAdapter.clearAndAddAll(data);
    }

    @OnClick(R.id.view_weather_day_item)
    public void onOnWeatherDayViewClicked() {
        int visibility = mHoursRecyclerView.getVisibility();

        if (visibility == View.GONE) {
            showWeatherHoursRecyclerView();
        } else {
            hideWeatherHoursRecyclerView();
        }
    }

    private void showWeatherHoursRecyclerView() {
        mHoursRecyclerView.setVisibility(View.VISIBLE);

        mArrowImageView
                .animate()
                .rotation(90)
                .start();
    }

    private void hideWeatherHoursRecyclerView() {
        mHoursRecyclerView.setVisibility(View.GONE);

        mArrowImageView
                .animate()
                .rotation(0)
                .start();
    }
}

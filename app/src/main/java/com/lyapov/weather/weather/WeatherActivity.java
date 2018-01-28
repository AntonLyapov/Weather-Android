package com.lyapov.weather.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.lyapov.weather.R;
import com.lyapov.weather.data.models.ForecastEntry;
import com.lyapov.weather.di.DI;
import com.lyapov.weather.weather.adapter.WeatherAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherActivity extends MvpActivity<WeatherView, WeatherPresenter> implements WeatherView, SwipeRefreshLayout.OnRefreshListener {

    static final String SAVE_INSTANCE_STATE_WEATHER = "weather";

    @BindView(R.id.srl_weather)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.pb_loading)
    public ProgressBar mProgressBar;

    @BindView(R.id.rv_weather)
    public RecyclerView mRecyclerView;

    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            List<ForecastEntry> forecastEntries =
                    (List<ForecastEntry>) savedInstanceState.getSerializable(SAVE_INSTANCE_STATE_WEATHER);
            mAdapter.clearAndAddAll(forecastEntries);
        } else {
            // Probably initialize members with default values for a new instance
            presenter.getRemoteWeatherData();
        }
    }

    private void setupSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        mAdapter = new WeatherAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @NonNull
    @Override
    public WeatherPresenter createPresenter() {
        return DI.provideWeatherPresenter();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<List<ForecastEntry>> forecastEntries) {
        mAdapter.clearAndAddAll(forecastEntries);
    }

    @Override
    public void showError(Throwable throwable) {
        mAdapter.clear();

        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, throwable.getLocalizedMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.activity_weather_error_retry_button_title), view -> {
                    presenter.getRemoteWeatherData();
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);

        presenter.getRemoteWeatherData();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the weather
        savedInstanceState.putSerializable(SAVE_INSTANCE_STATE_WEATHER, mAdapter.getObjects());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        List<ForecastEntry> forecastEntries =
                (List<ForecastEntry>) savedInstanceState.getSerializable(SAVE_INSTANCE_STATE_WEATHER);
        mAdapter.clearAndAddAll(forecastEntries);
    }
}

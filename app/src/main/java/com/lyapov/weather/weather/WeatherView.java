package com.lyapov.weather.weather;

import com.hannesdorfmann.mosby3.mvp.MvpView;
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
public interface WeatherView extends MvpView {
    void showLoading();

    void hideLoading();

    void showData(List<List<ForecastEntry>> forecastEntries);

    void showError(Throwable throwable);
}

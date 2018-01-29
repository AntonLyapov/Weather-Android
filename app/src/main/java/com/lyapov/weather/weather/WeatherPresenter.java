package com.lyapov.weather.weather;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.lyapov.weather.R;
import com.lyapov.weather.WeatherApplication;
import com.lyapov.weather.data.models.ForecastEntry;
import com.lyapov.weather.data.models.WeatherData;
import com.lyapov.weather.data.remote.WeatherRemoteDataSource;
import com.lyapov.weather.utils.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherPresenter implements MvpPresenter<WeatherView> {

    private final WeatherRemoteDataSource mWeatherRemoteDataSource;

    private WeatherView mWeatherView;

    public WeatherPresenter(final WeatherRemoteDataSource weatherRemoteDataSource) {
        mWeatherRemoteDataSource = weatherRemoteDataSource;
    }

    @Override
    public void attachView(@NonNull WeatherView view) {
        mWeatherView = view;
    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {
        mWeatherView = null;
    }

    public void getRemoteWeatherData() {
        String city = WeatherApplication.getContext().getString(R.string.weather_config_city);
        String countryCode = WeatherApplication.getContext().getString(R.string.weather_config_country_code);
        String units = WeatherApplication.getContext().getString(R.string.weather_config_units);

        mWeatherRemoteDataSource.getWeatherData(city, countryCode, units)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mWeatherView.showLoading())
                .doOnTerminate(() -> mWeatherView.hideLoading())
                .subscribe(this::test, throwable -> mWeatherView.showError(throwable));
    }

    private void test(WeatherData weatherData) {
        Observable.fromArray(weatherData.getList())
                .map(forecastEntries -> {
                    HashMap<Integer, List<ForecastEntry>> dayForecastEntryMap = new HashMap<>();

                    for (ForecastEntry forecastEntry : forecastEntries) {
                        int day = DateUtil.getDayOfYear(forecastEntry.getDate());

                        List<ForecastEntry> forecastForDay = dayForecastEntryMap.get(day);
                        if (forecastForDay == null) {
                            forecastEntries = new ArrayList<>();
                            dayForecastEntryMap.put(day, forecastEntries);
                        }

                        forecastEntries.add(forecastEntry);
                    }

                    List<Integer> keys = new ArrayList<>(dayForecastEntryMap.keySet());
                    Collections.sort(keys);

                    List<List<ForecastEntry>> result = new ArrayList<>();
                    for (Integer key : keys) {
                        result.add(dayForecastEntryMap.get(key));
                    }

                    return result;
                })
                .subscribe(forecastEntries -> mWeatherView.showData(forecastEntries));
    }
}

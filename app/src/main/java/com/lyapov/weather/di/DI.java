package com.lyapov.weather.di;

import com.lyapov.weather.data.remote.WeatherAPIService;
import com.lyapov.weather.data.remote.WeatherAPIServiceFactory;
import com.lyapov.weather.data.remote.WeatherRemoteDataSource;
import com.lyapov.weather.weather.WeatherPresenter;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class DI {

    public static WeatherPresenter provideWeatherPresenter() {
        return new WeatherPresenter(provideRemoteWeatherDataSource());
    }

    public static WeatherAPIService provideWeatherAPIService() {
        return new WeatherAPIServiceFactory().createWeatherAPIService();
    }

    public static WeatherRemoteDataSource provideRemoteWeatherDataSource(){
        return WeatherRemoteDataSource.getInstance();
    }
}

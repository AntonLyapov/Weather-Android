package com.lyapov.weather.di;

import com.lyapov.weather.data.remote.APIService;
import com.lyapov.weather.data.remote.APIServiceFactory;
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
        return new WeatherPresenter(provideRemoteDataSource());
    }

    public static APIService provideApiService() {
        return new APIServiceFactory().createApiService();
    }

    public static WeatherRemoteDataSource provideRemoteDataSource(){
        return WeatherRemoteDataSource.getInstance();
    }
}

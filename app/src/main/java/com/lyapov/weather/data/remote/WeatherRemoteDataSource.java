package com.lyapov.weather.data.remote;

import com.lyapov.weather.BuildConfig;
import com.lyapov.weather.data.WeatherDataSource;
import com.lyapov.weather.data.models.WeatherData;
import com.lyapov.weather.di.DI;

import java.util.Locale;

import io.reactivex.Observable;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherRemoteDataSource implements WeatherDataSource {
    private static WeatherRemoteDataSource mInstance;
    private APIService apiService;

    private WeatherRemoteDataSource(APIService apiService) {
        this.apiService = apiService;
    }

    public static WeatherRemoteDataSource getInstance() {
        if (mInstance == null) {
            synchronized (WeatherRemoteDataSource.class) {
                if (mInstance == null) {
                    mInstance = new WeatherRemoteDataSource(DI.provideApiService());
                }
            }
        }

        return mInstance;
    }

    public Observable<WeatherData> getWeatherData(String city, String countryCode, String units) {
        String query = String.format(Locale.US, "%s,%s", city, countryCode);

        return apiService.getWeatherForecast(BuildConfig.WEATHER_API_KEY, query, units);
    }
}

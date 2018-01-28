package com.lyapov.weather.data.remote;

import com.lyapov.weather.data.models.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public interface APIService {

    @GET("forecast")
    Observable<WeatherData> getWeatherForecast(
            @Query("appid") String apiKey,
            @Query("q") String query,
            @Query("units") String units
    );
}

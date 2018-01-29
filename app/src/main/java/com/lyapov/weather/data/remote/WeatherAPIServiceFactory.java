package com.lyapov.weather.data.remote;

import android.support.annotation.NonNull;

import com.lyapov.weather.BuildConfig;
import com.lyapov.weather.R;
import com.lyapov.weather.WeatherApplication;
import com.lyapov.weather.utils.NetworkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherAPIServiceFactory {

    private final Retrofit retrofit;

    public WeatherAPIServiceFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.WEATHER_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildHttpClient())
                .build();
    }

    public WeatherAPIService createWeatherAPIService() {
        return retrofit.create(WeatherAPIService.class);
    }

    @NonNull
    public OkHttpClient buildHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(BuildConfig.API_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(BuildConfig.API_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(createLoggingInterceptor());
        httpClient.addInterceptor(createNoNetworkInterceptor());

        return httpClient.build();
    }

    private Interceptor createLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    private static Interceptor createNoNetworkInterceptor() {
        return chain -> {
            if (!NetworkUtil.isNetworkConnected()) {
                String message = WeatherApplication.getContext()
                        .getString(R.string.activity_weather_error_no_internet_connection);

                throw new NoNetworkException(message);
            }
            return chain.proceed(chain.request());
        };
    }

    public static class NoNetworkException extends IOException {
        public NoNetworkException(String message) {
            super(message);
        }
    }
}

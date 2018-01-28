package com.lyapov.weather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lyapov.weather.WeatherApplication;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public final class NetworkUtil {

    private NetworkUtil() {
        // no instances allowed
    }

    public static boolean isNetworkConnected() {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) WeatherApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
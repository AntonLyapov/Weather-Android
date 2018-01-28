package com.lyapov.weather;

import android.app.Application;
import android.content.Context;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class WeatherApplication extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = this;
    }

    public static Context getContext() {
        return mApplicationContext;
    }
}

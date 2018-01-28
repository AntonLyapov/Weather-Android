package com.lyapov.weather.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class ForecastEntry implements Serializable {

    @Expose
    @SerializedName("dt")
    private long date;

    @Expose
    @SerializedName("dt_txt")
    private String dateText;

    @Expose
    @SerializedName("main")
    private ForecastMain main;

    @Expose
    @SerializedName("weather")
    private List<ForecastWeather> weather;


    public ForecastEntry(long date, String dateText, ForecastMain main, List<ForecastWeather> weather) {
        this.date = date;
        this.dateText = dateText;
        this.main = main;
        this.weather = weather;
    }


    public long getDate() {
        return date;
    }
    public void setDate(long date) {
        this.date = date;
    }


    public String getDateText() {
        return dateText;
    }
    public void setDateText(String dateText) {
        this.dateText = dateText;
    }


    public ForecastMain getMain() {
        return main;
    }
    public void setMain(ForecastMain main) {
        this.main = main;
    }


    public List<ForecastWeather> getWeather() {
        return weather;
    }
    public void setWeather(List<ForecastWeather> weather) {
        this.weather = weather;
    }
    public boolean hasWeather() {
        return weather != null && !weather.isEmpty();
    }
}

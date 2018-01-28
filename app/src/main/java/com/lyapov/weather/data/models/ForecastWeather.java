package com.lyapov.weather.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class ForecastWeather implements Serializable {

    @Expose
    @SerializedName("main")
    private String main;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("icon")
    private String icon;


    public ForecastWeather(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }


    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
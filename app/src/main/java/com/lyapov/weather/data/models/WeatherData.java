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
public class WeatherData implements Serializable {

    @Expose
    @SerializedName("cod")
    private String code;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("list")
    private List<ForecastEntry> list;


    public WeatherData(String code, String message, List<ForecastEntry> list) {
        this.code = code;
        this.message = message;
        this.list = list;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public List<ForecastEntry> getList() {
        return list;
    }
    public void setList(List<ForecastEntry> list) {
        this.list = list;
    }
}

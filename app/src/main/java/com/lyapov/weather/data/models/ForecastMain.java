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
public class ForecastMain implements Serializable {

    @Expose
    @SerializedName("temp")
    private float temp;

    @Expose
    @SerializedName("temp_min")
    private float tempMin;

    @Expose
    @SerializedName("temp_max")
    private float tempMax;

    @Expose
    @SerializedName("pressure")
    private float pressure;

    @Expose
    @SerializedName("humidity")
    private float humidity;


    public ForecastMain(float temp, float tempMin, float tempMax, float pressure, float humidity) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }


    public float getTemp() {
        return temp;
    }
    public void setTemp(float temp) {
        this.temp = temp;
    }


    public float getTempMin() {
        return tempMin;
    }
    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }


    public float getTempMax() {
        return tempMax;
    }
    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }


    public float getPressure() {
        return pressure;
    }
    public void setPressure(float pressure) {
        this.pressure = pressure;
    }


    public float getHumidity() {
        return humidity;
    }
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
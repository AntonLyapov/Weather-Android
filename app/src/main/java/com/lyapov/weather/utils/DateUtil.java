package com.lyapov.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public class DateUtil {

    private DateUtil() {

    }

    /**
     * Get day of year from timestamp
     * @param timeAsSeconds timestamp in seconds
     * @return
     */
    public static int getDayOfYear(long timeAsSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeAsSeconds * 1000);

        return calendar.get(Calendar.DAY_OF_YEAR);
    }


    /**
     * Get day of week from timestamp
     * @param timeAsSeconds timestamp in seconds
     * @return
     */
    public static String getDayOfWeek(long timeAsSeconds) {
        Date date = new Date(timeAsSeconds * 1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        return dateFormat.format(date);
    }


    /**
     * Get time from timestamp
     * @param timeAsSeconds timestamp in seconds
     * @return
     */
    public static String getHourAndMinutes(long timeAsSeconds) {
        Date date = new Date(timeAsSeconds * 1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }
}

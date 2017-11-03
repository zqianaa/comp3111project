package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/11/2.
 */
import java.util.Date;
import java.util.Calendar;
public class TimeGetter {
    Calendar c = Calendar.getInstance();
    public int gethour() {
        int hour = c.get(Calendar.HOUR_OF_DAY) + 8;
        return hour;
    }

    public int getminute() {
        int minute = c.get(Calendar.MINUTE);
        return minute;
    }
}

package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/11/17.
 */
import java.util.Calendar;
public class CurrTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int second;

    public CurrTime() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minutes = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);
    }

    public int getyear () {
        return year;
    }

    public int getmonth () {
        return month;
    }

    public int getday () {
        return day;
    }

    public int gethour () {
        return hour;
    }

    public int getminurtes () {
        return minutes;
    }

    public int getsecond () {
        return second;
    }

}

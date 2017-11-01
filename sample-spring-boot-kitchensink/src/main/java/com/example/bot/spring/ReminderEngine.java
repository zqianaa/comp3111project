package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import java.util.Date;
import java.util.Calendar;
import java.util.Timer;
public class ReminderEngine {
    Timer timer;
    int hour;
    int minutes;
    int seconds;
    public ReminderEngine(int h, int m, int s) {
        hour = h;
        minutes = m;
        seconds = s;
        Date time = getTime();
        timer = new Timer();
        timer.schedule(new ReminderEngineHelper(),time);
    }
    public Date getTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE,minutes);
        c.set(Calendar.SECOND,seconds);
        Date time = c.getTime();
        return time;
    }
}
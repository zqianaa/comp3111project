package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import java.util.Date;
import java.util.Calendar;
import java.util.Timer;
public class ReminderEngine {
    Timer timer;
    public ReminderEngine() {
        Date time = getTime();
        System.out.println("指定时间time=" + time);
        timer = new Timer();
        timer.schedule(new ReminderEngineHelper(),time);
    }
    public Date getTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 17);
        c.set(Calendar.MINUTE,20);
        c.set(Calendar.SECOND,00);
        Date time = c.getTime();
        return time;
    }
}

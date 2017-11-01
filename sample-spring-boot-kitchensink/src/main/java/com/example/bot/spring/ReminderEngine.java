package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import java.util.TimerTask;
import java.util.Date;
import java.util.Calendar;
import java.util.Timer;
public class ReminderEngine {
    private Timer timer;
    private int hour;
    private int minutes;
    private int seconds;
    private final int period = 24*3600*1000;
    private KitchenSinkController kc = new KitchenSinkController();
    public ReminderEngine(int h, int m, int s, KitchenSinkController kc, String UserID) {
        hour = h;
        minutes = m;
        seconds = s;
        this.kc = kc;
        int time = getTime();
        kc.reminder(String.valueOf(time));
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kc.reminder("test");
            }
        };
        timer = new Timer();
        try {
            timer.schedule(task, time, period);
        } catch (Exception e) {
            kc.reminder("error");
        }
    }
    public int getTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);
        int curhour = c.get(Calendar.HOUR_OF_DAY) + 8;
        int curminute = c.get(Calendar.MINUTE);
        int sumhour = 0;
        int summinutes = 0;
        kc.reminder("curhour " + String.valueOf(curhour));
        kc.reminder("curminute " +String.valueOf(curminute));
        kc.reminder("hourset " + String.valueOf(hour));
        kc.reminder("minuteset " + String.valueOf(minutes));
        if (curhour == hour) {
            sumhour = 0;
        } else
        if (curhour > hour) {
            sumhour = (23 - curhour) + hour;
            summinutes = (60 - curminute) + minutes;
        } else {
            if (curminute > minutes) {
                sumhour = hour - curhour - 1;
                summinutes = 60 - (curminute - minutes);
            } else if (curminute == minutes) {
                summinutes = 0;
            } else {
                sumhour = hour - curhour;
                summinutes = minutes - curminute;
            }
        }
        int time = sumhour*3600*1000 + summinutes*60*1000;
        return time;
    }
}
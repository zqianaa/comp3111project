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
    public ReminderEngine(int h, int m, int s, KitchenSinkController kc, String UserID) {
        hour = h;
        minutes = m;
        seconds = s;
        Date time = getTime();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kc.reminder("test");
            }
        };
        timer = new Timer();
        timer.schedule(task,time);
        kc.reminder(time.toString());
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
package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import java.util.TimerTask;
public class ReminderEngineHelper extends TimerTask{
    KitchenSinkController kc;
    public ReminderEngineHelper (KitchenSinkController kc) {
        this.kc = kc;
    }
    @Override
    public void run() {
        String remind = "Please enter today's menu";
        kc.reminder(remind);
    }
}

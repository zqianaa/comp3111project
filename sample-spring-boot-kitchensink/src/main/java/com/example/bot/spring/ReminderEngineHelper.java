package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import java.util.TimerTask;
public class ReminderEngineHelper extends TimerTask{
    @Override
    public void run() {
        KitchenSinkController kc = new KitchenSinkController();
        String remind = "Please enter today's menu";
        kc.reminder(remind);
    }
}

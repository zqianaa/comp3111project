package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.TimerTask;
public class ReminderEngineHelper extends TimerTask{
    private LineMessagingClient lineMessagingClient;
    private String UserID;
    KitchenSinkController kc;
    public ReminderEngineHelper (KitchenSinkController kc, String UserID) {
        this.kc = kc;
        this.UserID = UserID;
    }
    @Override
    public void run() {
        PushMessage pushMessage = new PushMessage(UserID, new TextMessage("test"));
        lineMessagingClient.pushMessage(pushMessage);
    }
}

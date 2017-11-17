package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/11/17.
 */
public class JudgeTime {

    private CurrTime ct;
    private String USERID;
    private KitchenSinkController kc;

    public JudgeTime (CurrTime tc, String USERID, KitchenSinkController kc) {
        ct = tc;
        this.USERID = USERID;
        this.kc = kc;
    }


}

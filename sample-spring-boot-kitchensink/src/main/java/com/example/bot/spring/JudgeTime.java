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

    public boolean judge () {
        SQLSearchTime sst = new SQLSearchTime(USERID, kc);
        boolean rt = false;
        sst.Search();
        if (sst.getyear() > ct.getyear()) {
            rt = true;
        } else if (sst.getyear() == ct.getyear()) {
            if (sst.getmonth() > ct.getmonth()) {
                rt = true;
            } else if (sst.getmonth() == ct.getmonth()) {
                if (sst.getday() > ct.getday()) {
                    rt = true;
                } else if (sst.getday() == ct.getday()) {
                    if (sst.gethour() > ct.gethour()) {
                        rt = true;
                    } else if (sst.gethour() == ct.gethour()) {
                        if (sst.getminutes() > ct.getminurtes()) {
                            rt = true;
                        } else if (sst.getminutes() == ct.getminurtes()) {
                            if (sst.getsecond() > ct.getsecond()) {
                                rt = true;
                            } else if (sst.getsecond() == ct.getsecond()) {
                                    rt = true;
                            } else {
                                rt =false;
                            }
                        } else {
                            rt =false;
                        }
                    } else {
                        rt =false;
                    }
                } else {
                    rt =false;
                }
            } else {
                rt =false;
            }
        } else {
            rt =false;
        }
        return rt;
    }


}

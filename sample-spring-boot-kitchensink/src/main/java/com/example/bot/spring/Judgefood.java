package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/11/19.
 */
public class Judgefood {
    /**
     * Created by qwmqza on 2017/11/19.
     */
    private KitchenSinkController kc;
    private String food;
    private String USERID;

    public Judgefood (String food, String USERID, KitchenSinkController kc) {
        this.kc = kc;
        this.food = food;
        this.USERID = USERID;
    }

    public boolean judge() {
        SQLSearchDislike SSD = new SQLSearchDislike(USERID,kc);
        SSD.Search();
        SQLSearchLike SSL = new SQLSearchLike(USERID, kc);
        SSL.Search();
        String [] getfood = food.split(",");
        if (getfood[0].equals(SSD.getDislike1()) || getfood[0].equals(SSD.getDislike2()) || getfood[0].equals(SSD.getDislike3())) {
            return false;
        }

        if (getfood[0].equals(SSL.getDislike1()) || getfood[0].equals(SSL.getDislike2()) || getfood[0].equals(SSL.getDislike3())) {
            return true;
        }
        //等其他method
        return true;
    }
}


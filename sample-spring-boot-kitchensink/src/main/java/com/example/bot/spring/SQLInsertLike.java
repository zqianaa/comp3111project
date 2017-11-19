package com.example.bot.spring;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by qwmqza on 2017/11/18.
 */
public class SQLInsertLike {

    private String [] like = new String[3];
    private String [] dislike = new String[3];
    private KitchenSinkController kc;
    private String USERID;

    public SQLInsertLike(String USERID, String [] like, String [] dislike, KitchenSinkController kc) {
        for (int i = 0; i < like.length; i++) {
            this.like[i] = like[i];
        }
        for (int i = like.length; i < 3; i++) {
            this.like[i] = "";
        }
        for (int i = 0; i < dislike.length; i++) {
            this.dislike[i] = dislike[i];
        }
        for (int i = dislike.length; i < 3; i++) {
            this.dislike[i] = "";
        }
        this.kc = kc;
        this.USERID = USERID;
    }

    public void insert() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO liketable " +
                    "VALUES ('"+ USERID + "','" + like[0] + "','" + like[1] + "','" + like[2] + "','" + dislike[0] + "','" + dislike[1] + "','" + dislike[2] + "');");
            conn.close();
        } catch (Exception e) {
            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! ");
        }
    }

}

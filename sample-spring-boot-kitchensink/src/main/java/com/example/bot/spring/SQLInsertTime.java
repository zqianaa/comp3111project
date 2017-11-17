package com.example.bot.spring;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by qwmqza on 2017/11/17.
 */
public class SQLInsertTime implements SQLInsertion{

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int seconds;
    private KitchenSinkController kc;
    private String USERID;

    public SQLInsertTime(String USERID, int y, int mo, int d, int h, int mi, int s, KitchenSinkController kc) {
        year = y;
        month = mo;
        day = d;
        hour = h;
        minutes = mi;
        seconds = s;
        this.kc = kc;
        this.USERID = USERID;
    }

    public void Insert() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO timetable " +
                    "VALUES ('"+ USERID + "','" + year + "','" + month + "','" + day + "','" + hour + "','" + minutes + "','"+ seconds + "'" + ");");
            conn.close();
        } catch (Exception e) {
            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! ");
        }
    }
}

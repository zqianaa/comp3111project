package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/17.
 */
public class SQLSearchTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int second;
    private KitchenSinkController kc;
    private String USERID;

    public SQLSearchTime(String USERID, KitchenSinkController kc) {
        this.USERID = USERID;
        this.kc = kc;
    }

    public String Search() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM codetable where userid like concat('%', ?, '%')");
            stmt.setString(1, USERID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(USERID)) {
                    year = Integer.parseInt(rs.getString(2));
                    month = Integer.parseInt(rs.getString(3));
                    day = Integer.parseInt(rs.getString(4));
                    hour = Integer.parseInt(rs.getString(5));
                    minutes = Integer.parseInt(rs.getString(6));
                    second = Integer.parseInt(rs.getString(7));
                    break;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return null;


    }

    public int getyear () {
        return year;
    }

    public int getmonth () {
        return month;
    }

    public int getday () {
        return day;
    }

    public int gethour () {
        return hour;
    }

    public int getminutes () {
        return minutes;
    }

    public int getsecond () {
        return second;
    }

}
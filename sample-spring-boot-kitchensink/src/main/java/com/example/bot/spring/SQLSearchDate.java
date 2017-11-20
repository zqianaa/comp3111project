package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/11/20.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLSearchDate {

    private String USERID;
    private String TableName;
    private KitchenSinkController kc;
    private String date;

    public SQLSearchDate(String USERID, String TableName, KitchenSinkController kc , String date) {
        this.USERID = USERID;
        this.TableName = TableName;
        this.kc = kc;
        this.date = date;
    }

    public boolean search() {
        boolean rt = false;
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement ("SELECT * FROM " + TableName + " where userid like concat('%', ?, '%')");
            stmt.setString(1, USERID);
            ResultSet rs = stmt.executeQuery();
            CurrTime cdate= new CurrTime();
            date=cdate.getday()+ "/" + cdate.getmonth();
            kc.reminder("this is a test for SQLSearchDate");
            while (rs.next()) {
                if (rs.getString(1).equals(date)) {
                    rt = true;
                    break;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return rt;
    }
}



package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/17.
 */
public class SQLSearchUserID {

    private String USERID;
    private String TableName;
    private KitchenSinkController kc;

    public SQLSearchUserID(String USERID, String TableName, KitchenSinkController kc) {
        this.USERID = USERID;
        this.TableName = TableName;
        this.kc = kc;
    }

    public boolean search() {
        boolean rt = false;
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement ("SELECT * FROM " + TableName + " where userid like concat('%', ?, '%')");
            stmt.setString(1, USERID);
            ResultSet rs = stmt.executeQuery();
            kc.reminder("this is a test");
            while (rs.next()) {
                if (rs.getString(1).equals(USERID)) {
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

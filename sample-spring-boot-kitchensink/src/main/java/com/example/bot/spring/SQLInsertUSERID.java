package com.example.bot.spring;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by qwmqza on 2017/11/17.
 */
public class SQLInsertUSERID implements SQLInsertion {

    private String USERID;
    private KitchenSinkController kc;

    public SQLInsertUSERID(String USERID, KitchenSinkController kc) {
        this.USERID = USERID;
        this.kc = kc;
    }

    public void Insert() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO useridtable " +
                    "VALUES ('"+ USERID + "');");
            conn.close();
        } catch (Exception e) {
            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! ");
        }
    }
}

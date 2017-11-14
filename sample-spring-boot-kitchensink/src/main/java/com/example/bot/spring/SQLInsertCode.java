package com.example.bot.spring;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by qwmqza on 2017/11/12.
 */
public class SQLInsertCode implements SQLInsertion {
    private int code;
    private String USERID;
    private KitchenSinkController kc;

    public SQLInsertCode(int code, String USERID, KitchenSinkController kc) {
        this.code = code;
        this.USERID = USERID;
        this.kc = kc;
    }

    @Override
    public void Insert() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO codetable " +
                    "VALUES ('"+ USERID + "','" + code + "'" + ");");
            conn.close();
        } catch (Exception e) {
            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! ");
        }
    }
}

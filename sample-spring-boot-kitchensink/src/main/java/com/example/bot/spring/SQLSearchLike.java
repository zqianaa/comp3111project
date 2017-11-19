package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/18.
 */
public class SQLSearchLike {

    private KitchenSinkController kc;
    private String USERID;
    private String like1;
    private String like2;
    private String like3;

    public SQLSearchLike(String USERID, KitchenSinkController kc) {
        this.USERID = USERID;
        this.kc = kc;
    }

    public String Search() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM liketable where userid like concat('%', ?, '%')");
            stmt.setString(1, USERID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(USERID)) {
                    like1 = rs.getString(2);
                    like2 = rs.getString(3);
                    like3 = rs.getString(4);
                    break;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return null;
    }
    public String getDislike1() {
        return like1;
    }

    public String getDislike2() {
        return like2;
    }

    public String getDislike3() {
        return like3;
    }

}

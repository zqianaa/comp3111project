package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/18.
 */
public class SQLSearchDislike implements SQLSearching{

    private String USERID;
    private KitchenSinkController kc;
    private String dislike1;
    private String dislike2;
    private String dislike3;

    public SQLSearchDislike(String USERID, KitchenSinkController kc) {
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
                    dislike1 = rs.getString(5);
                    dislike2 = rs.getString(6);
                    dislike3 = rs.getString(7);
                    break;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return null;
    }
    public String getDislike1() {
        return dislike1;
    }

    public String getDislike2() {
        return dislike2;
    }

    public String getDislike3() {
        return dislike3;
    }

}

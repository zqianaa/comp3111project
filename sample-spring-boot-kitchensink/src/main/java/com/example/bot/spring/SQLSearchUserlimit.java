package com.example.bot.spring;

import sun.jvm.hotspot.code.CodeBlob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLSearchUserlimit implements SQLSearching{
    String target;
    KitchenSinkController kc;

    public SQLSearchUserlimit(String target, KitchenSinkController kc) {
        this.target = target;
        this.kc = kc;
    }

    public String Search() {
        String result = null;
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement ("SELECT * FROM customertable where USERID = ?");
            stmt.setString(1, target);
            ResultSet rs = stmt.executeQuery();

            while(result == null && rs.next()) {
                if (rs.getString(1).equals(target)) {
                    result = rs.getString(6);

                }
            }
            if (result == null) {
                throw new Exception("CODE NOT FOUND");
            }
        }
        catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return result;

    }
}

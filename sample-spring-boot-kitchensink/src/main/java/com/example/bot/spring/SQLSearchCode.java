package com.example.bot.spring;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/12.
 */
public class SQLSearchCode implements SQLSearching {

    private String Code;
    private KitchenSinkController kc;
    public SQLSearchCode(String Code, KitchenSinkController kc) {
        this.Code = Code;
        this.kc = kc;
    }

    public String Search() {
        String USERID = null;
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement ("SELECT * FROM respond where input like concat('%', ?, '%')");
            stmt.setString(1, Code);
            ResultSet rs = stmt.executeQuery();
            while (USERID == null && rs.next()) {
                if (rs.getString(2).equals(Code)) {
                    USERID = rs.getString(1);
                }
            }
            if (USERID == null) {
                throw new Exception("CODE NOT FOUND");
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return USERID;
    }

}

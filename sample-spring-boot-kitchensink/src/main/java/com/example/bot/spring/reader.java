package com.example.bot.spring;
import java.sql.Connection;
import java.sql.Statement;

import sun.jvm.hotspot.code.CodeBlob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sun.jvm.hotspot.code.CodeBlob;
/**
 * Created by qwmqza on 2017/11/12.
 */
public class reader implements SQLSearching{
    private String Code;
    private KitchenSinkController kc;
    public reader(String Code, KitchenSinkController kc) {
        this.Code = Code;
        this.kc = kc;
    }
    public String Search() {
        string string = null;
        try {

            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM respond where input like concat('%', ?, '%')");
            stmt.setString(1, Code);
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while (string == null && rs.next()) {
                if (rs.getString(2).equals(Code)) {
                    string = rs.getString(2);
                    string = string + " " + rs.getString(3);
                    string = string + " " + rs.getString(4);
                }
            }
            if (string == null) {
                throw new Exception("CODE NOT FOUND");
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return string ;
    }

}

package com.example.bot.spring;
import java.sql.Connection;
import java.sql.Statement;


public class SQLCreateTable {
    private KitchenSinkController kc;
    private String USERID;

    public SQLCreateTable(String USERID, KitchenSinkController kc) {
        this.USERID = USERID;
        this.kc = kc;
    }
    public void Create() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE table " + USERID + " (Date varchar(50) primary key,meal varchar(20),"
                    + "food1 varchar(50),price1 int, food2 varchar(50),price2 int, food3 varchar (50), price3 int;)");
            conn.close();
        } catch (Exception e) {
            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! ");
        }
    }
}



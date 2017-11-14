package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLInsert implements SQLInsertion {
    String uSERID;
    int w;
    int h;
    int a;
    String data;
    KitchenSinkController kc;

    public SQLInsert (String uSERID ,int w, int h, int a, String data, KitchenSinkController kc ) {
        this.a = a;
        this.data = data;
        this.kc = kc;
        this.uSERID = uSERID;
        this.w = w;
        this.h = h;
    }

    public void Insert() {
        try {
            SQLDatabaseEngine engine=new SQLDatabaseEngine(kc) ;
            Connection conn =engine.getConnection() ;
            Statement st = conn.createStatement();
            if(data.equals("M")) {

                int b =(int) (66.47+ (13.75* w) + (5.0*h) - (6.75* a));
                st.executeUpdate("INSERT INTO customertable " +
                        "VALUES ('"+ uSERID + "'," + w +","+ h +","+ a + ", 'M'," + b +");");

            }
            else if (data.equals("F")) {
                int b =(int) (66.47+ (13.75* w) + (5.0*h) - (6.75* a));
                st.executeUpdate("INSERT INTO customertable " +
                        "VALUES ('"+ uSERID + "'," + w +","+ h +","+ a + ", 'F'," + b +");");

            }

            conn.close();
        } catch (Exception e) {

            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! ");


        }
    }


}

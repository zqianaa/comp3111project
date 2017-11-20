package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLInsertMenu implements SQLInsertion {
    private KitchenSinkController kc;
    private String USERID;
    private String [] food;
    private CurrTime ctime = new CurrTime();
    private String time;
    private String [] price;
    private String meals;


    public SQLInsertMenu (String USERID, String meal,String [] food, String [] price,KitchenSinkController kc) {
        this.USERID = USERID;
        this.kc = kc;
        this.food = new String[3];
        this.price = new String[3];
        for (int i = 0; i < food.length; i++) {
            this.food[i] = food[i];
        }
        for (int i = 0; i < price.length; i++) {
            this.price[i] = price[i];
        }
        this.meals = meal;
    }

    public void Insert() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            Statement st = conn.createStatement();
            time = ctime.getday()+"/"+ctime.getmonth();
            if (meals.equals("breakfast")) {
                time = time + "1";
            }
            if (meals.equals("lunch")) {
                time = time + "2";
            }
            if (meals.equals("dinner")) {
                time = time + "3";
            }
            st.executeUpdate("INSERT INTO " + USERID + " VALUES ( '"+ time  + "','" + food[0]+"','"+price[0]+"','"+food[1]+"','"
                        +price[1] +"','"+food[2]+"','"+price[2]+"');");
        }
        catch (Exception e) {
            kc.reminder(e.getMessage());
            kc.reminder("Got an exception! this");
        }
    }
}


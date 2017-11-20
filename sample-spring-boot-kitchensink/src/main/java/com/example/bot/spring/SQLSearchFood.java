package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/12.
 */
public class SQLSearchFood implements SQLSearching{
    private String USERID;
    private KitchenSinkController kc;
    private String date;
    private String meal;
    private String check;
    public SQLSearchFood(String USERID, KitchenSinkController kc, String date , String meal) {
        this.USERID=USERID;
        this.kc=kc;
        this.date=date;
        this.meal = meal;

    }
    public String Search() {
        String string = null;
        try {
            if (meal.equals("breakfast")){
                check = "1";
            }else if (meal.equals("lunch")){
                check="2";
            }else{
                check="3";
            }
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + USERID + " where date like concat('%', ?, '%')");
            stmt.setString(1, date+check);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                kc.reminder("test1");
                if (rs.getString(1).substring(0,5).equals(date) && rs.getString(1).substring(5).equals(check)) {
                    kc.reminder("test2");
                    string = rs.getString(2);
                    string = string + "," + rs.getString(4);
                    string = string + "," + rs.getString(6);
                }
            }
            if (string == null) {
                throw new Exception("THE" + meal + "of date" + date + "is  NOT FOUND");
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage()+"that");
        }
        return string ;
    }

}


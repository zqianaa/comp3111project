package com.example.bot.spring;

import javax.xml.soap.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qwmqza on 2017/11/12.
 */
public class Calculation  {
    private String food;
    private int cal;
    private int na;
    private int fat;
    private String [] temp1;
    private KitchenSinkController kc;

    public Calculation(String meal ,String USERID, KitchenSinkController kc) {
        CurrTime currTime = new CurrTime();
        String date = currTime.getday() + "/" + currTime.getmonth();
        SQLSearchFood ssf = new SQLSearchFood(USERID, kc, date, meal);
        food = ssf.Search();
        this.kc = kc;
    }

    public String Calucate() {
        String [] foodstring = food.split(",");
        String [] reply = new String[3];
        try {
            for(int i = 0 ; i < foodstring.length; i++) {
                int cal1 = 0;
                int na1 = 0;
                int fat1 = 0;
                if (foodstring[i] != null) {
                    temp1 = foodstring[i].split(" ");
                    for (int j = 0; j < temp1.length; ++j) {
                        SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
                        Connection conn = engine.getConnection();
                        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable");
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            if (rs.getString(1).split(",")[0].equals(temp1[j])) {
                                cal1 = cal1 + Integer.parseInt(rs.getString(5));
                                na1 = na1 + Integer.parseInt(rs.getString(6));
                                fat1 = fat1 + Integer.parseInt(rs.getString(7));
                                cal += cal1;
                                na += na1;
                                fat += fat1;
                                break;
                            }
                        }
                    }
                }
                if (cal1 == 0 || foodstring[i] == null) {
                    reply[i] = "FOOD NOT FOUND";
                } else {
                    reply[i] = foodstring[i] + "    Energy:" + cal1 + "    Na:" + na1 + "    Fat:" + fat1;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        String string = reply[0] + "\n" +reply[1] + "\n" + reply[2] + "\n" + "Total Energy:" + cal + "/n" + "Total Na:" + na + "\n" + "Total Fat" + fat + "\n";
        return string;
    }

}


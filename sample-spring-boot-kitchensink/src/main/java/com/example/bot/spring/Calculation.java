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
    private double cal;
    private double na;
    private double fat;
    private String [] temp1;
    private KitchenSinkController kc;
    private String[] foodstring;

    public Calculation(String meal ,String USERID, KitchenSinkController kc) {
        CurrTime currTime = new CurrTime();
        String date = currTime.getday() + "/" + currTime.getmonth();
        SQLSearchFood ssf = new SQLSearchFood(USERID, kc, date, meal);
        food = ssf.Search();
        this.kc = kc;
        foodstring = food.split(",");
        kc.reminder(foodstring[0]);
    }

    public String Calucate() {

        String [] reply = new String[3];
        try {
            for(int i = 0 ; i < foodstring.length; i++) {
                double cal1 = 0;
                double na1 = 0;
                double fat1 = 0;
                kc.reminder(foodstring[i]);
                if (foodstring[i] != null) {
                    temp1 = foodstring[i].split(" ");
                    for (int j = 0; j < temp1.length; ++j) {
                        kc.reminder(temp1[j]);
                        SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
                        Connection conn = engine.getConnection();
                        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable");
                        ResultSet rs = stmt.executeQuery();
                        int c = 0;
                        while (rs.next()) {
                            if (c == 6688) {
                                String haha = rs.getString(2).split(",")[0].toLowerCase();
                                kc.reminder(haha);
                            }
                            c++;
                            if (rs.getString(2).split(",")[0].toLowerCase().equals(temp1[j])) {
                                cal1 = cal1 + Double.parseDouble(rs.getString(5));
                                na1 = na1 + Double.parseDouble(rs.getString(6));
                                fat1 = fat1 + Double.parseDouble(rs.getString(7));
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
            kc.reminder(e.getMessage()+"this");
        }
        String string = reply[0] + "\n" +reply[1] + "\n" + reply[2] + "\n" + "Total Energy:" + cal + "\n" + "Total Na:" + na + "\n" + "Total Fat" + fat + "\n";
        return string;
    }

    public double getcal() {
        return cal;
    }

    public String getfood1() {
        if (!foodstring[0].equals("null")) {
            return foodstring[0];
        } else {
            return "";
        }
    }
    public String getfood2() {
        if (!foodstring[1].equals("null")) {
            return foodstring[1];
        } else {
            return "";
        }
    }
    public String getfood3() {
        if (!foodstring[2].equals("null")) {
            return foodstring[2];
        } else {
            return "";
        }
    }

}


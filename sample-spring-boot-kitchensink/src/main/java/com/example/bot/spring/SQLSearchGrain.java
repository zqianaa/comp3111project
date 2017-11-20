package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/**
 * Created by qwmqza on 2017/11/19.
 */
public class SQLSearchGrain {
    private KitchenSinkController kc;
    private String [] grain = new String[10000];
    private String [] Measure = new String[10000];
    private String [] Energy = new String [10000];
    private String [] Na = new String[10000];
    private String [] Fatty = new String[10000];
    private String [] Good = new String[10000];
    private String [] Bad = new String[10000];
    private int mark = 0;
    private int num;

    public SQLSearchGrain(KitchenSinkController kc) {
        this.kc = kc;
    }

    public String Search() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable where description like concat('%', ?, '%')");
            stmt.setString(1, "Rice");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).substring(0,4).equals("Rice")) {
                    grain[mark] = rs.getString(2);
                    Measure[mark] = rs.getString(3);
                    Energy[mark] = rs.getString(4);
                    Na[mark] = rs.getString(5);
                    Fatty[mark] = rs.getString(6);
                    Good[mark] = rs.getString(7);
                    Bad[mark] = rs.getString(8);
                    mark++;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return null;
    }

    public String Search1() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable where description like concat('%', ?, '%')");
            stmt.setString(1, "Chicken");
            ResultSet rs = stmt.executeQuery();
            int mark = 0;
            while (rs.next()) {
                if (rs.getString(2).substring(0,7).equals("Noodles")) {
                    grain[mark] = rs.getString(2);
                    Measure[mark] = rs.getString(3);
                    Energy[mark] = rs.getString(4);
                    Na[mark] = rs.getString(5);
                    Fatty[mark] = rs.getString(6);
                    Good[mark] = rs.getString(7);
                    Bad[mark] = rs.getString(8);
                    mark++;
                }
            }
        } catch (Exception e) {
            kc.reminder(e.getMessage());
        }
        return null;
    }

    public String Search2() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable");
            ResultSet rs = stmt.executeQuery();
            int mark = 0;
            int c = 0;
            while (rs.next()) {
                String test = rs.getString(2);
                c++;
                if (c == 1927) {
                    kc.reminder("test");
                    kc.reminder(test);
                }
                if (test.substring(0,5).equals("Bread")) {
                    grain[mark] = rs.getString(2);
                    Measure[mark] = rs.getString(3);
                    Energy[mark] = rs.getString(4);
                    Na[mark] = rs.getString(5);
                    Fatty[mark] = rs.getString(6);
                    Good[mark] = rs.getString(7);
                    Bad[mark] = rs.getString(8);
                    kc.reminder(Good[mark]);
                    mark++;
                }
            }
            kc.reminder(String.valueOf(c));
        } catch (Exception e) {
            kc.reminder(e.getMessage() + "this");
        }
        return null;
    }

    public void getnumber() {
        kc.reminder(String.valueOf(mark));
        Random rd = new Random();
        num = rd.nextInt(mark);
        kc.reminder(String.valueOf(num));
    }

    public String getvege() {
        return grain[num];
    }

    public String getmeasure() {
        return Measure[num];
    }

    public String getenergy() {
        return Energy[num];
    }

    public String getna() {
        return Na[num];
    }

    public String getfatty() {
        return Fatty[num];
    }

    public String getgood() {
        return Good[num];
    }

    public String getbad() {
        return Bad[num];
    }

}

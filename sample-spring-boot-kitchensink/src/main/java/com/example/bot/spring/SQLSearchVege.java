package com.example.bot.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
/**
 * Created by qwmqza on 2017/11/19.
 */
public class SQLSearchVege implements SQLSearching {

    private KitchenSinkController kc;
    private String [] vege;
    private String [] Measure;
    private String [] Energy;
    private String [] Na;
    private String [] Fatty;
    private String [] Good;
    private String [] Bad;
    private int mark = 0;
    private int num;

    public SQLSearchVege(KitchenSinkController kc) {
        this.kc = kc;
    }

    public String Search() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable where description like concat('%', ?, '%')");
            stmt.setString(1, "Salad");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).substring(0,5).equals("Salad")) {
                    vege[mark] = rs.getString(2);
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
            stmt.setString(1, "Carrot");
            ResultSet rs = stmt.executeQuery();
            int mark = 0;
            while (rs.next()) {
                if (rs.getString(2).substring(0,6).equals("Carrot")) {
                    vege[mark] = rs.getString(2);
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
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable where description like concat('%', ?, '%')");
            stmt.setString(1, "Broccoli");
            ResultSet rs = stmt.executeQuery();
            int mark = 0;
            while (rs.next()) {
                if (rs.getString(2).substring(0,8).equals("Broccoli")) {
                    vege[mark] = rs.getString(2);
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

    public String Search3() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable where description like concat('%', ?, '%')");
            stmt.setString(1, "Cabbage");
            ResultSet rs = stmt.executeQuery();
            int mark = 0;
            while (rs.next()) {
                if (rs.getString(2).substring(0,7).equals("Cabbage")) {
                    vege[mark] = rs.getString(2);
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

    public String Search4() {
        try {
            SQLDatabaseEngine engine = new SQLDatabaseEngine(kc);
            Connection conn = engine.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM datatable where description like concat('%', ?, '%')");
            stmt.setString(1, "Mushroom");
            ResultSet rs = stmt.executeQuery();
            int mark = 0;
            while (rs.next()) {
                if (rs.getString(2).substring(0,8).equals("Mushroom")) {
                    vege[mark] = rs.getString(2);
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

    public void getnumber() {
        Random rd = new Random();
        num = rd.nextInt(mark);
    }

    public String getvege() {
        return vege[num];
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

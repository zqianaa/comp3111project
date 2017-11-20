package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/11/19.
 */
public class MenuEnginee {

    private KitchenSinkController kc;
    private SQLSearchFruit SSF;
    private SQLSearchMeat SSM2;
    private SQLSearchMilk SSM;
    private SQLSearchGrain SSG;
    private SQLSearchVege SSV;
    private String USERID;

    public MenuEnginee(String USERID,KitchenSinkController kc) {
        this.kc = kc;
        this.USERID = USERID;
        SSG = new SQLSearchGrain(kc);
        SSM = new SQLSearchMilk(kc);
        SSM2 = new SQLSearchMeat(kc);
        SSF = new SQLSearchFruit(kc);
        SSV = new SQLSearchVege(kc);
    }

    public String Generatebreakfast() {
        while (true) {
            SSG.Search2();
            SSG.getnumber();
            Judgefood jf = new Judgefood(SSG.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        kc.reminder(SSG.getvege() + "test");
        String reply1 = SSG.getvege() + " Energy(kcal):" + SSG.getenergy() + " Measure:" + SSG.getmeasure() + " Na(mg):" + SSG.getna() + " Fatty Acid" + SSG.getfatty() + " Benifit" + SSG.getgood();
            while (true) {
            SSM.Search();
            SSM.Search1();
            SSM.getnumber();
            Judgefood jf = new Judgefood(SSM.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        kc.reminder(SSM.getvege() + "test");
        String reply2 = SSM.getvege() + " Energy(kcal):" + SSM.getenergy() + " Measure:" + SSM.getmeasure() + " Na(mg):" + SSM.getna() + " Fatty Acid" + SSM.getfatty() + " Benifit" + SSM.getgood();
        while (true) {
            SSF.Search();
            SSF.Search1();
            SSF.Search2();
            SSF.Search3();
            SSF.Search4();
            SSF.getnumber();
            Judgefood jf = new Judgefood(SSG.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        kc.reminder(SSF.getmeat() + "test");
        String reply3 = SSF.getmeat() + " Energy(kcal):" + SSF.getenergy() + " Measure:" + SSF.getmeasure() + " Na(mg):" + SSF.getna() + " Fatty Acid" + SSF.getfatty() + " Benifit" + SSF.getgood();
        while (true) {
            SSM2.Search4();
            SSM2.getnumber();
            Judgefood jf = new Judgefood(SSM2.getmeat(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        kc.reminder(SSM2.getmeat() + "test");
        String reply4 = SSM2.getmeat() + " Energy(kcal):" + SSM2.getenergy() + " Measure:" + SSM2.getmeasure() + " Na(mg):" + SSM2.getna() + " Fatty Acid" + SSM2.getfatty() + " Benifit" + SSM2.getgood();
        return "For breakfast, today's recommendation is \n" + reply1 + "\n" + reply2 + "\n"+ reply3 + "\n" + reply4;
    }

    public String Generatelunch() {
        while (true) {
            SSG.Search();
            SSG.Search1();
            SSG.getnumber();
            Judgefood jf = new Judgefood(SSG.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply1 = SSG.getvege() + " Energy(kcal):" + SSG.getenergy() + " Measure:" + SSG.getmeasure() + " Na(mg):" + SSG.getna() + " Fatty Acid" + SSG.getfatty() + " Benifit" + SSG.getgood();
        while (true) {
            SSM.Search1();
            SSM.getnumber();
            Judgefood jf = new Judgefood(SSM.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply2 = SSM.getvege() + " Energy(kcal):" + SSM.getenergy() + " Measure:" + SSM.getmeasure() + " Na(mg):" + SSM.getna() + " Fatty Acid" + SSM.getfatty() + " Benifit" + SSM.getgood();
        while (true) {
            SSF.Search();
            SSF.Search1();
            SSF.Search2();
            SSF.Search3();
            SSF.Search4();
            SSF.getnumber();
            Judgefood jf = new Judgefood(SSG.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply3 = SSF.getmeat() + " Energy(kcal):" + SSF.getenergy() + " Measure:" + SSF.getmeasure() + " Na(mg):" + SSF.getna() + " Fatty Acid" + SSF.getfatty() + " Benifit" + SSF.getgood();
        while (true) {
            SSM2.Search();
            SSM2.Search1();
            SSM2.Search2();
            SSM2.Search3();
            SSM2.Search4();
            SSM2.getnumber();
            Judgefood jf = new Judgefood(SSM2.getmeat(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply4 = SSM2.getmeat() + " Energy(kcal):" + SSM2.getenergy() + " Measure:" + SSM2.getmeasure() + " Na(mg):" + SSM2.getna() + " Fatty Acid" + SSM2.getfatty() + " Benifit" + SSM2.getgood();
        while (true) {
            SSV.Search();
            SSV.Search1();
            SSV.Search2();
            SSV.Search3();
            SSV.Search4();
            SSV.getnumber();
            Judgefood jf = new Judgefood(SSV.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply5 = SSV.getvege() + " Energy(kcal):" + SSV.getenergy() + " Measure:" + SSV.getmeasure() + " Na(mg):" + SSV.getna() + " Fatty Acid" + SSV.getfatty() + " Benifit" + SSV.getgood();
        return "For breakfast, today's recommendation is \n" + reply1 + "\n" + reply2 + "\n"+ reply3 + "\n" + reply4 + "\n" + reply5;
    }

    public String Generatedinner() {
        while (true) {
            SSG.Search2();
            SSG.getnumber();
            Judgefood jf = new Judgefood(SSG.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply1 = SSG.getvege() + " Energy(kcal):" + SSG.getenergy() + " Measure:" + SSG.getmeasure() + " Na(mg):" + SSG.getna() + " Fatty Acid" + SSG.getfatty() + " Benifit" + SSG.getgood();
        while (true) {
            SSM.Search();
            SSM.Search1();
            SSM.getnumber();
            Judgefood jf = new Judgefood(SSM.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply2 = SSM.getvege() + " Energy(kcal):" + SSM.getenergy() + " Measure:" + SSM.getmeasure() + " Na(mg):" + SSM.getna() + " Fatty Acid" + SSM.getfatty() + " Benifit" + SSM.getgood();
        while (true) {
            SSF.Search();
            SSF.Search1();
            SSF.Search2();
            SSF.Search3();
            SSF.Search4();
            SSF.getnumber();
            Judgefood jf = new Judgefood(SSG.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply3 = SSF.getmeat() + " Energy(kcal):" + SSF.getenergy() + " Measure:" + SSF.getmeasure() + " Na(mg):" + SSF.getna() + " Fatty Acid" + SSF.getfatty() + " Benifit" + SSF.getgood();
        while (true) {
            SSM2.Search();
            SSM2.Search1();
            SSM2.getnumber();
            Judgefood jf = new Judgefood(SSM2.getmeat(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply4 = SSM2.getmeat() + " Energy(kcal):" + SSM2.getenergy() + " Measure:" + SSM2.getmeasure() + " Na(mg):" + SSM2.getna() + " Fatty Acid" + SSM2.getfatty() + " Benifit" + SSM2.getgood();
        while (true) {
            SSV.Search();
            SSV.Search1();
            SSV.Search2();
            SSV.Search3();
            SSV.Search4();
            SSV.getnumber();
            Judgefood jf = new Judgefood(SSV.getvege(), USERID, kc);
            if (jf.judge()) {
                break;
            }
        }
        String reply5 = SSV.getvege() + " Energy(kcal):" + SSV.getenergy() + " Measure:" + SSV.getmeasure() + " Na(mg):" + SSV.getna() + " Fatty Acid" + SSV.getfatty() + " Benifit" + SSV.getgood();
        return "For breakfast, today's recommendation is \n" + reply1 + "\n" + reply2 + "\n"+ reply3 + "\n" + reply4 + "\n" + reply5;
    }


}

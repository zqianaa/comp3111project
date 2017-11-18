package com.example.bot.spring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
/**
 * Created by qwmqza on 2017/11/12.
 */
public class getCode {

    private ArrayList<Integer> CodeSet;

    private Random rd;

    public getCode() {
        CodeSet = new ArrayList<>();
        rd = new Random();
        while(CodeSet.size() <= 5000) {
            int num = rd.nextInt(1000000);
            if (num >= 100000 && num < 1000000 && !CodeSet.contains(num)) {
                CodeSet.add(num);
            }
        }
    }

    public int getnumber() {
        int rt = CodeSet.get(0);
        CodeSet.remove(0);
        return rt;
    }

}

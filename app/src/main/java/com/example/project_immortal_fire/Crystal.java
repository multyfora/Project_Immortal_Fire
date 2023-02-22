package com.example.project_immortal_fire;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class Crystal {

    private static int hp1 = 100;
    private static int hp2 = 100;

    @SuppressLint("SetTextI18n")
    public static void setHp1(int hp, TextView HP) {
        hp1 = hp;
        HP.setText(hp + "");
    }

    public static int getHp1() {
        return hp1;
    }

    public static void dmg1(int dmg, TextView HP) {
        hp1 -= dmg;
        HP.setText(hp1);
    }


    @SuppressLint("SetTextI18n")
    public static void setHp2(int hp, TextView HP) {
        hp2 = hp;
        HP.setText(hp+ "");
    }

    public static int getHp2() {
        return hp2;
    }

    public static void dmg2(int dmg, TextView HP) {
        hp2 -= dmg;
        HP.setText(hp2);
    }

}

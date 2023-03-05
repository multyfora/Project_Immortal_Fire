package com.example.project_immortal_fire;

import android.annotation.SuppressLint;
import android.util.Log;
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

    //*requires renew method!
    public static void dmg1(int dmg) {
        hp1 -= dmg;
    }


    @SuppressLint("SetTextI18n")
    public static void setHp2(int hp, TextView HP) {
        hp2 = hp;
        HP.setText(hp + "");
    }

    public static int getHp2() {
        return hp2;
    }

    //*requires renew method!
    public static void dmg2(int dmg) {
        hp2 -= dmg;
    }

    public static void renew1(TextView Hp2) {
        Hp2.setText(hp2 + "");
    }

    public static void renew2(TextView Hp1) {
        Hp1.setText(hp1 + "");
    }
    public static void HpCheck(){
        if(hp1<1){
            //!call game ending method
            PlayerTwo.GameOver2();
        }
        if (hp2<1){
            //!call game ending method
            PlayerOne.GameOver1();
        }
    }
}

package com.example.project_immortal_fire;

import android.widget.ImageView;

public class BoardCards {


    public static void remove(String[] BoardCards,String[] EnemyCards, ImageView[] BCards, int num){

        if(num<5) {
            BoardCards[num] = null;
        }
        else {
            EnemyCards[num] = null;

        }
        BCards[num].setImageResource(R.drawable.placeholder);

    }
}

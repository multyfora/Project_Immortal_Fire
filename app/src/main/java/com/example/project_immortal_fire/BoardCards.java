package com.example.project_immortal_fire;

import android.util.Log;
import android.widget.ImageView;

import java.util.Arrays;

public class BoardCards {

    static String TAG = "BoardCardsClass";

    public static void remove(String[] BoardCards,String[] EnemyCards, ImageView[] BCards, int num){

        //!if boardCards is null then i know i should damage the crystal

        if(num<5) {
            Log.i(TAG, "removed: " + BoardCards[num]);
            BoardCards[num] = null;

        }
        else {
            Log.i(TAG, "removed: " + EnemyCards[num-6]);
            EnemyCards[num-5] = null;

        }
        BCards[num].setImageResource(R.drawable.placeholder);


    }
}

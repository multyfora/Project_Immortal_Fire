package com.example.project_immortal_fire;

import android.util.Log;
import android.widget.ImageView;

import java.util.Arrays;

public class BoardCards {

    static String TAG = "BoardCardsClass";

    public static void remove(String[] BoardCards,String[] EnemyCards, ImageView[] BCards, int num){

        //!if boardCards is null then i know i should damage the crystal!!!!

        if(num<6) {
            Log.i(TAG, "removed: " + BoardCards[num]);
            if (BoardCards[num]!=null) {
                BoardCards[num] = null;
            }
            else {
                Crystal.dmg1(Cards.getATK(EnemyCards[num]));
            }
        }
        else {
            Log.i(TAG, "removed: " + EnemyCards[num-6]);
            if(EnemyCards[num-6]!=null) {
                EnemyCards[num - 6] = null;
            }
            else {
                Crystal.dmg2(Cards.getATK(BoardCards[num-6]));
            }

        }
        Log.i(TAG, "bebra: " + Arrays.toString(EnemyCards));
        BCards[num].setImageResource(R.drawable.placeholder);


    }
}

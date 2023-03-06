package com.example.project_immortal_fire;

import static com.example.project_immortal_fire.CardsSet.string;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class BoardCards {

    static String TAG = "BoardCardsClass";

    public static void remove(String[] BoardCards,String[] EnemyCards, ImageView[] BCards, int num){


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
        BCards[num].setImageResource(R.drawable.placeholder);


    }

    public static void renew(int[] BoardHp, int[] EnemyHp,String[] BoardCards, String[] EnemyCards, TextView[] BText){
        Log.i(TAG, "renew CALLED ");
        for (int i = 0; i < BoardCards.length; i++) {
            if(BoardCards[i]!=null && !Objects.equals(BoardCards[i], "none")){
                BText[i].setText(string[Integer.parseInt(BoardCards[i].charAt(BoardCards[i].length() - 2) + String.valueOf(BoardCards[i].charAt(BoardCards[i].length() - 1)))]);
                Log.i(TAG, "BText: " + BText[i].getText());
            }


        }
    }
}

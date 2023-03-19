package com.example.project_immortal_fire;

import static com.example.project_immortal_fire.CardsSet.string;

import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class BoardCards {

    static String TAG = "BoardCardsClass";

    static TextView[] BoardText;

    static boolean First = true;

    public static void remove(String[] BoardCards, String[] EnemyCards, ImageView[] BCards, int num) {


        if (num < 6) {
            Log.i(TAG, "removed: " + BoardCards[num]);
            if (BoardCards[num] != null) {
                BoardCards[num] = null;
            } else {
                Crystal.dmg1(Cards.getATK(EnemyCards[num]));
            }
        } else {
            Log.i(TAG, "removed: " + EnemyCards[num - 6]);
            if (EnemyCards[num - 6] != null) {
                EnemyCards[num - 6] = null;
            } else {
                Crystal.dmg2(Cards.getATK(BoardCards[num - 6]));
            }

        }
        BCards[num].setImageResource(R.drawable.placeholder);


    }

    public static void placed(TextView Text, String BoardCard){
        if(BoardCard!=null) {
            Text.setText(string[Integer.parseInt(BoardCard.charAt(BoardCard.length() - 2) + String.valueOf(BoardCard.charAt(BoardCard.length() - 1)))]);
        }
    }


    public static void renew(int[] BoardHp, int[] EnemyHp, String[] BoardCards, String[] EnemyCards, TextView[] BText) {
        Log.i(TAG, "BoardHp: " + Arrays.toString(BoardHp) + "\nEnemyHp: " + Arrays.toString(EnemyHp));
        Log.i(TAG, "renew CALLED ");
        for (int i = 0; i < BoardCards.length; i++) {
            if (BoardCards[i] != null && !Objects.equals(BoardCards[i], "none")) {
                BText[i].setText(string[Integer.parseInt(BoardCards[i].charAt(BoardCards[i].length() - 2) + String.valueOf(BoardCards[i].charAt(BoardCards[i].length() - 1)))]);
                String buffer = (String) BText[i].getText();
                Log.i(TAG, "BoardHp: " + BoardHp[i] + " i: " + i + " buffer: " + buffer  + " BoardCard: " + BoardCards[i]);
                if(BoardHp[i]!=0) {
                    BText[i].setText(buffer.replace(Cards.getHP(BoardCards[i]) + "", BoardHp[i] + ""));
                }
                Log.i(TAG, "BText: " + BText[i].getText());
            } else {
                BText[i].setText("");
            }

            for (int j = 0; j < EnemyCards.length; j++) {
                if (EnemyCards[j] != null && !Objects.equals(EnemyCards[j], "none")) {
                    BText[j+6].setText(string[Integer.parseInt(EnemyCards[j].charAt(EnemyCards[j].length() - 2) + String.valueOf(EnemyCards[j].charAt(EnemyCards[j].length() - 1)))]);
                    String buffer = (String) BText[j+6].getText();
                    if(EnemyHp[j]!=0) {
                        BText[j + 6].setText(buffer.replace(Cards.getHP(EnemyCards[j]) + "", EnemyHp[j] + ""));
                    }
                    Log.i(TAG, "BText: " + BText[j].getText());
                } else {
                    BText[j+6].setText("");
                }
            }

            Log.i(TAG, "BText: " + Arrays.toString(BText));
            BoardText = BText.clone();
            First = false;
        }
    }
}


package com.example.project_immortal_fire;

import static android.content.ContentValues.TAG;

import static com.example.project_immortal_fire.Cards.AllCards;
import static com.example.project_immortal_fire.PlayerOne.CardsArr;
import static com.example.project_immortal_fire.PlayerTwo.CardsArr2;
import static com.example.project_immortal_fire.PlayerOne.CardsCount;

import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class shuffler {


    public static void shuffle(boolean num){
        String[] BetterCards = new String[CardsCount];
        System.arraycopy(AllCards,0, BetterCards,0,CardsCount);
        //shuffling
        List<String> intList = Arrays.asList(BetterCards);
        Collections.shuffle(intList);
        intList.toArray(BetterCards);
        int i = 0;
        Log.i(TAG, "shuffle better cards: " + Arrays.toString(BetterCards) + " :: " + "Cards arr: " + Arrays.toString(CardsArr)  + " :: all cards: " + Arrays.toString(AllCards));
        while (i<CardsArr.length){
            if(num){
                if (CardsArr2[i].equals("none")) {
                    CardsArr2[i] = BetterCards[i];
                }
            }else {
            if (CardsArr[i].equals("none")) {
                CardsArr[i] = BetterCards[i];
            }
            }

                i++;

        }
    }
}

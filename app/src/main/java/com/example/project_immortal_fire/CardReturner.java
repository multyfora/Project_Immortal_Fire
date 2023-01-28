package com.example.project_immortal_fire;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;

import java.util.Arrays;

public class CardReturner {
    private static int counter = 0;
    static int No = -1;


    public static void Return(MaterialCardView card1, MaterialCardView card2, MaterialCardView card3, MaterialCardView card4, MaterialCardView card5,
                              ImageView card1Image, ImageView card2Image, ImageView card3Image, ImageView card4Image, ImageView card5Image,
                              String[] CardsArr, String[] buffer, int AvailableBoardSlots){
        counter++;
        Log.i("CardReturner", "counter: " + counter);
        if (counter >= AvailableBoardSlots){

            No = Integer.parseInt(buffer[1]);
            CardsArr[No] = buffer[0];
            Log.i("CardReturner", "worked " + "No: " + No + " buffer: " + Arrays.toString(buffer) + " CardsArr: " + Arrays.toString(CardsArr));
            CardsSet.set(card1Image,card2Image,card3Image,card4Image,card5Image,CardsArr);
            switch (buffer[1]){
                case "0": card1.setVisibility(View.VISIBLE);
                break;
                case "1": card2.setVisibility(View.VISIBLE);
                break;
                case "2": card3.setVisibility(View.VISIBLE);
                break;
                case "3": card4.setVisibility(View.VISIBLE);
                break;
                case "4": card5.setVisibility(View.VISIBLE);
                break;

            }
            counter = 0;
        }
    }
}

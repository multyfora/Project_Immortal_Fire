package com.example.project_immortal_fire;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class CardReturner {
    private static int counter = 0;
    static int No = -1;


    public static void Return(TextView card1, TextView card2, TextView card3, TextView card4, TextView card5, String[] CardsArr, String[] buffer, int AvailableBoardSlots){
        counter++;
        Log.i("CardReturner", "counter: " + counter);
        if (counter >= AvailableBoardSlots){

            No = Integer.parseInt(buffer[1]);
            CardsArr[No] = buffer[0];
            Log.i("CardReturner", "worked " + "No: " + No + " buffer: " + Arrays.toString(buffer) + " CardsArr: " + Arrays.toString(CardsArr));
            CardsSet.set(card1,card2,card3,card4,card5,CardsArr);
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

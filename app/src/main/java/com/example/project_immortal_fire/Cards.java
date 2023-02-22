package com.example.project_immortal_fire;


import static com.example.project_immortal_fire.CardsSet.draw;

import android.util.Log;
import android.widget.ImageView;

import java.util.Arrays;

public abstract class Cards {
    public final static String[] AllCards = new String[]{"salamander_fire_card01", "phoenix_fire_card02", "dragon_fire_card03", "kirin_fire_card04", "lion_fire_card05", "koi_fish_water_card06", "leviathan_water_card07", "shark_water_card08", "09", "10", "raiden_electro_card11", "mjolnir_electro_card12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", };
    public static int[] ATK = new int[]{-1,12,10,20,11,13,5,23,12,-1,-1,21,15};
    public static int[] HP = new int[]{-1,10,11,20,11,15,30,25,13,-1,-1,18,15};

    static int[] BoardHp = new int[6];
    static int[] EnemyHp = new int[6];

    static String TAG = "Cards";

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }



    public static int getHP(String CardName){
        if(CardName!=null) {
            int key = Integer.parseInt(CardName.charAt(CardName.length() - 2) + String.valueOf(CardName.charAt(CardName.length() - 1)));
            return HP[key];
        }else return 0;
    }


    public static int getATK(String CardName){
        if(CardName!=null) {
            int key = Integer.parseInt(CardName.charAt(CardName.length() - 2) + String.valueOf(CardName.charAt(CardName.length() - 1)));
            return ATK[key];
        }else return 0;
    }


    //setting by number of card
    public static void setHP(int num,int hp){
        HP[num] = hp;
    }
    //setting by the name of card
    public static void setHP(String CardName,int hp){
        int key = Integer.parseInt(CardName.charAt(CardName.length() - 2)+String.valueOf(CardName.charAt(CardName.length() - 1)));
        HP[key] = hp;
    }
    //setting by number of card
    public static void setAtk(int num,int atk){
        ATK[num] = atk;
    }
    //setting by the name of card
    public static void setAtk(String CardName,int atk){
        int key = Integer.parseInt(CardName.charAt(CardName.length() - 2)+String.valueOf(CardName.charAt(CardName.length() - 1)));
        ATK[key] = atk;
    }


    public static void boardSet(String dragData, ImageView BoardCard){
        int key = Integer.parseInt(dragData.charAt(dragData.length() - 2)+String.valueOf(dragData.charAt(dragData.length() - 1)));
        BoardCard.setImageResource(draw[key]);

    }


    public static void Moved(String[] boardCards, String[] enemyCards, ImageView[] BCards){

        Log.i(TAG, "string a: " + Arrays.toString(boardCards) +"\nstring b: " + Arrays.toString(enemyCards));

        int[] AtkBuffer = new int[6];
        int[] HpBuffer = new int[6];

        //* first step

        //damaging enemy cards
        //taking away atk of frien from hp of enemy

        for (int i = 0; i < 6; i++) {

            AtkBuffer[i] =getATK(boardCards[i]);
            HpBuffer[i] =getHP(enemyCards[i]);
            EnemyHp = Arrays.copyOf(HpBuffer,HpBuffer.length);

        }
        Log.i(TAG, "ENEMY BEFORE: " + Arrays.toString(EnemyHp));
        for (int i = 0; i < 6; i++) {

            HpBuffer[i] -= AtkBuffer[i];
        }
        EnemyHp = Arrays.copyOf(HpBuffer,HpBuffer.length);
        Log.i(TAG, "ENEMY AFTER: " + Arrays.toString(EnemyHp));

        //* second step

        //cleaning buffers
        Arrays.fill(AtkBuffer,0);
        Arrays.fill(HpBuffer,0);

        //damaging frien cards
        //taking away atk of enemy from hp of frien

        for (int i = 0; i < 6; i++) {

            AtkBuffer[i] =getATK(enemyCards[i]);
            HpBuffer[i] =getHP(boardCards[i]);
            BoardHp = Arrays.copyOf(HpBuffer,HpBuffer.length);
        }
        Log.i(TAG, "ALLY BEFORE: " + Arrays.toString(BoardHp));

        for (int i = 0; i < 6; i++) {

            HpBuffer[i] -= AtkBuffer[i];
        }
        BoardHp = Arrays.copyOf(HpBuffer,HpBuffer.length);
        Log.i(TAG, "ALLY AFTER: " + Arrays.toString(BoardHp));


        //* third step
        //making all <0 hp cards disappear

        //call card disappearing method

        for (int board = 0; board < 6; board++) {

            if(BoardHp[board]<0){
                BoardCards.remove(boardCards,enemyCards,BCards,board);
            }
        }
        for (int enemy = 0; enemy < 6; enemy++) {

            if(EnemyHp[enemy]<0){
                BoardCards.remove(boardCards,enemyCards,BCards,enemy+6);
            }
        }

    }
}
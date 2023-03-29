package com.example.project_immortal_fire;


import static com.example.project_immortal_fire.CardsSet.draw;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Objects;

public abstract class Cards {
    public final static String[] AllCards = new String[]{"salamander_fire_card01", "phoenix_fire_card02", "dragon_fire_card03", "kirin_fire_card04", "lion_fire_card05", "koi_fish_water_card06", "leviathan_water_card07", "shark_water_card08", "09", "10", "raiden_electro_card11", "mjolnir_electro_card12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",};
    public static int[] ATK = new int[]{-1, 12, 10, 20, 11, 13, 5, 23, 12, -1, -1, 21, 15};
    public static int[] HP = new int[]{-1, 10, 11, 20, 11, 15, 30, 25, 13, -1, -1, 18, 15};

    static int[] BoardHp = new int[6];
    static int[] EnemyHp = new int[6];

    static String Pyro = "Pyro";
    static String Hydro = "Hydro";
    static String Electro = "Electro";
    static String Anemo = "Anemo";
    static String Darkness = "Darkness";
    static boolean called = false;
    static boolean IsBurning = false;

    static String TAG = "CardsAAAA";

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }


    public static int getHP(String CardName) {
        if (CardName != null) {
            int key = Integer.parseInt(CardName.charAt(CardName.length() - 2) + String.valueOf(CardName.charAt(CardName.length() - 1)));
            return HP[key];
        } else return 0;
    }


    public static int getATK(String CardName) {
        if (CardName != null) {
            int key = Integer.parseInt(CardName.charAt(CardName.length() - 2) + String.valueOf(CardName.charAt(CardName.length() - 1)));
            return ATK[key];
        } else return 0;
    }


    //setting by number of card
    public static void setHP(int num, int hp) {
        HP[num] = hp;
    }

    //setting by the name of card
    public static void setHP(String CardName, int hp) {
        int key = Integer.parseInt(CardName.charAt(CardName.length() - 2) + String.valueOf(CardName.charAt(CardName.length() - 1)));
        HP[key] = hp;
    }

    //setting by number of card
    public static void setAtk(int num, int atk) {
        ATK[num] = atk;
    }

    //setting by the name of card
    public static void setAtk(String CardName, int atk) {
        int key = Integer.parseInt(CardName.charAt(CardName.length() - 2) + String.valueOf(CardName.charAt(CardName.length() - 1)));
        ATK[key] = atk;
    }


    public static void ApplyElement(String Element){
        if(Objects.equals(Element, Pyro)){

        }
    }

    public static String getElement(String Card) {
        if (Card != null) {
            int key = Integer.parseInt(Card.charAt(Card.length() - 2) + String.valueOf(Card.charAt(Card.length() - 1)));
            if (key > 0 && key <= 5) {
                return Pyro;
            }
            if (key > 5 && key <= 10) {
                return Hydro;
            }
        }
        return "err";
    }

    public static void boardSet(String dragData, ImageView BoardCard) {
        Log.i(TAG, "key: " + dragData);
        int key = Integer.parseInt(dragData.charAt(dragData.length() - 2) + String.valueOf(dragData.charAt(dragData.length() - 1)));
        BoardCard.setImageResource(draw[key]);

    }


    public static void UpdateHp(String[] BoardCards, String[] EnemyCards, TextView[] BText, int Player){
        if (Player==1){
            com.example.project_immortal_fire.BoardCards.renew(EnemyHp, BoardHp,BoardCards,EnemyCards,BText);
        }
        else if(Player==2){
            com.example.project_immortal_fire.BoardCards.renew(BoardHp, EnemyHp,BoardCards,EnemyCards,BText);
        }


    }

    public static void SetCalled(){
        called = false;
    }

    public static void Moved(String[] boardCards, String[] enemyCards, ImageView[] BCards, TextView[] BText) {
        if(!called) {
            Log.i(TAG, "string a: " + Arrays.toString(boardCards) + "\nstring b: " + Arrays.toString(enemyCards));

            int[] AtkBuffer = new int[6];
            int[] AtkBuffer2 = new int[6];
            boolean[] damaged = new boolean[12];

            //* first step


            //setting buffers
            for (int i = 0; i < 6; i++) {

                AtkBuffer[i] = getATK(boardCards[i]);
                if (EnemyHp[i] == 0) {
                    EnemyHp[i] = getHP(enemyCards[i]);
                }
            }
            for (int i = 0; i < 6; i++) {

                AtkBuffer2[i] = getATK(enemyCards[i]);
                if (BoardHp[i] == 0) {
                    BoardHp[i] = getHP(boardCards[i]);
                }
            }

            //damaging enemy cards
            //taking away atk of frien from hp of enemy

            Log.i(TAG, "ENEMY BEFORE: " + Arrays.toString(EnemyHp));
            for (int i = 0; i < 6; i++) {
                if (enemyCards[i] != null) {
                    if (Objects.equals(getElement(enemyCards[i]), Pyro)) {
                        EnemyHp[i] -= AtkBuffer[i];
                        BoardHp[i] = BoardHp[i] - 1;
                    } else EnemyHp[i] -= AtkBuffer[i];
                    if (AtkBuffer[i] != 0) {
                        damaged[i] = true;
                    }
                }
                Log.i(TAG, "Enemycards: " + enemyCards[i]);
            }
            Log.i(TAG, "ENEMY AFTER: " + Arrays.toString(EnemyHp));

            //* second step

            //cleaning buffers
            Arrays.fill(AtkBuffer, 0);

            //damaging frien cards
            //taking away atk of enemy from hp of frien


            Log.i(TAG, "ALLY BEFORE: " + Arrays.toString(BoardHp));

            for (int i = 0; i < 6; i++) {
                if (boardCards[i] != null) {
                    if (Objects.equals(getElement(boardCards[i]), Pyro)) {
                        BoardHp[i] -= AtkBuffer2[i];
                        EnemyHp[i] = EnemyHp[i] - 1;
                    } else BoardHp[i] -= AtkBuffer2[i];
                    if (AtkBuffer2[i] != 0) {
                        damaged[i + 6] = true;
                    }
                }
                Log.i(TAG, "BoardCards: " + boardCards[i]);
            }
            Log.i(TAG, "ALLY AFTER: " + Arrays.toString(BoardHp));


            //*second (and a half)-th step
            //applying elemental effects
            for (int i = 0; i < 6; i++) {
                if (boardCards[i] != null) {
                    ApplyElement(getElement(boardCards[i]));
                }
            }


            //* third step

            //making all <1 hp cards disappear

            //call card disappearing method

            for (int board = 0; board < 6; board++) {

                if (BoardHp[board] < 1) {
                    Crystal.dmg1(Math.abs(BoardHp[board]));
                    BoardHp[board] = 0;
                    BoardCards.remove(boardCards, enemyCards, BCards, board);
                }
            }
            for (int enemy = 0; enemy < 6; enemy++) {

                if (EnemyHp[enemy] < 1) {
                    Crystal.dmg1(Math.abs(EnemyHp[enemy]));
                    EnemyHp[enemy] = 0;
                    BoardCards.remove(boardCards, enemyCards, BCards, enemy + 6);
                }
            }

            //* forth step

            //updating the visual hp for all board cards

            BoardCards.renew(BoardHp, EnemyHp, boardCards, enemyCards, BText);


            //* fifth step

            //shaking animation

            ValueAnimator shake = ValueAnimator.ofInt(0, 300);
            shake.setDuration(600);
            shake.setInterpolator(new LinearInterpolator());
            shake.start();
            shake.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    int shakeAnimatedValue = (int) shake.getAnimatedValue();
                    for (int i = 0; i < 12; i++) {
                        if ((BText[i].getText() != "" && damaged[i])) {
                            BCards[i].setTranslationY((float) (Math.sin(shakeAnimatedValue * 10)) * 15);
                            BText[i].setTranslationY((float) (Math.sin(shakeAnimatedValue * 10)) * 15);
                        }
                    }
                }
            });
            shake.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    for (int i = 0; i < 12; i++) {
                        BCards[i].setTranslationY(0);
                        BText[i].setTranslationY(0);
                    }

                }
            });
            called = true;
        }
    }
}
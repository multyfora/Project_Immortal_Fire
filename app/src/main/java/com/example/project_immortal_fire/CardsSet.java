package com.example.project_immortal_fire;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CardsSet {

    public static int NullCount(String[] BoardCards){
        int count = 0;
        for (Object element : BoardCards) {
            if (element == null) {
                count++;
            }
        }
        return count;
    }

    public static int[] draw ={-1,R.drawable.salamander_fire_card01,R.drawable.phoenix_fire_card02,R.drawable.dragon_fire_card03,R.drawable.kirin_fire_card04,R.drawable.lion_fire_card05,R.drawable.koi_fish_water_card06,R.drawable.leviathan_water_card07,R.drawable.shark_water_card08,R.drawable.a09,R.drawable.a10,R.drawable.raiden_electro_card11,R.drawable.mjolnir_electro_card12,R.drawable.a13,R.drawable.a14,R.drawable.a15,R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21,R.drawable.a22,R.drawable.a23,R.drawable.a24,R.drawable.a25};
    public static int[] string ={-1,R.string.salamander_fire_card01,R.string.phoenix_fire_card02,R.string.dragon_fire_card03,R.string.kirin_fire_card04,R.string.lion_fire_card05,R.string.koi_fish_water_card06,R.string.leviathan_water_card07,R.string.shark_water_card08,R.string.a09,R.string.a10,R.string.raiden_electro_card11,R.string.mjolnir_electro_card12,R.string.a13,R.string.a14,R.string.a15,R.string.a16,R.string.a17,R.string.a18,R.string.a19,R.string.a20,R.string.a21,R.string.a22,R.string.a23,R.string.a24,R.string.a25};

    public static void renew(TextView card1, TextView card2, TextView card3, TextView card4, TextView card5, String[] arr ){
        for (int i = 0; i < 5; i++) {
            if(arr[i] != null){
            if(arr[i].equals("none")){
                switch (i) {
                    case 0:
                        card1.setVisibility(View.GONE);
                        break;
                    case 1:
                        card2.setVisibility(View.GONE);
                        break;
                    case 2:
                        card3.setVisibility(View.GONE);
                        break;
                    case 3:
                        card4.setVisibility(View.GONE);
                        break;
                    case 4:
                        card5.setVisibility(View.GONE);
                        break;
                }
                }
            }
        }
    }

    public static void enemyset(ImageView EnemyCard1, ImageView EnemyCard2, ImageView EnemyCard3, ImageView EnemyCard4, ImageView EnemyCard5, ImageView EnemyCard6, String[] arr ){
        int[] newarr = new int[6];
        for (int i = 0; i < 6; i++) {
            if(arr[i] != null){
                if (!arr[i].equals("none")) {
                    newarr[i] = Integer.parseInt(arr[i].charAt(arr[i].length() - 2)+String.valueOf(arr[i].charAt(arr[i].length() - 1)));
                } else {
                    newarr[i] = -1;
                }
            }
            else  newarr[i] = -1;
        }
        if(newarr[0]!=-1) {
            EnemyCard1.setImageResource(draw[newarr[0]]);
        }
        if(newarr[1]!=-1) {
            EnemyCard2.setImageResource(draw[newarr[1]]);
        }
        if(newarr[2]!=-1) {
            EnemyCard3.setImageResource(draw[newarr[2]]);
        }
        if(newarr[3]!=-1) {
            EnemyCard4.setImageResource(draw[newarr[3]]);
        }
        if(newarr[4]!=-1) {
            EnemyCard5.setImageResource(draw[newarr[4]]);
        }
        if(newarr[5]!=-1) {
            EnemyCard6.setImageResource(draw[newarr[5]]);
        }

    }



    public static void boardset(ImageView BoardCard1, ImageView BoardCard2, ImageView BoardCard3, ImageView BoardCard4, ImageView BoardCard5, ImageView BoardCard6, String[] arr ) {

        int[] newarr = new int[6];
        for (int i = 0; i < 6; i++) {
            if (arr[i] != null) {
                if (!arr[i].equals("none")) {
                    newarr[i] = Integer.parseInt(arr[i].charAt(arr[i].length() - 2)+String.valueOf(arr[i].charAt(arr[i].length() - 1)));
                } else {
                    newarr[i] = -1;
                }
            }
            else  newarr[i] = -1;
        }

        if(newarr[0]!=-1) {
            BoardCard1.setImageResource(draw[newarr[0]]);
        }
        if(newarr[1]!=-1) {
            BoardCard2.setImageResource(draw[newarr[1]]);
        }
        if(newarr[2]!=-1) {
            BoardCard3.setImageResource(draw[newarr[2]]);
        }
        if(newarr[3]!=-1) {
            BoardCard4.setImageResource(draw[newarr[3]]);
        }
        if(newarr[4]!=-1) {
            BoardCard5.setImageResource(draw[newarr[4]]);
        }
        if(newarr[5]!=-1) {
            BoardCard6.setImageResource(draw[newarr[5]]);
        }
    }


    public static void set(TextView card1, TextView card2, TextView card3, TextView card4, TextView card5, String[] arr) {

        int[] newarr = new int[5];
        for (int i = 0; i < 5; i++) {
            if (!arr[i].equals("none")){
                newarr[i] = Integer.parseInt(arr[i].charAt(arr[i].length() - 2)+String.valueOf(arr[i].charAt(arr[i].length() - 1)));
            }
            else {newarr[i] = -1;}
        }

        if(newarr[0]!=-1) {
            card1.setText(string[newarr[0]]);
            card1.setBackgroundResource(draw[newarr[0]]);
        }
        if(newarr[1]!=-1) {
            card2.setText(string[newarr[1]]);
            card2.setBackgroundResource(draw[newarr[1]]);
        }
        if(newarr[2]!=-1) {
            card3.setText(string[newarr[2]]);
            card3.setBackgroundResource(draw[newarr[2]]);
        }
        if(newarr[3]!=-1) {
            card4.setText(string[newarr[3]]);
            card4.setBackgroundResource(draw[newarr[3]]);
        }
        if(newarr[4]!=-1) {
            card5.setText(string[newarr[4]]);
            card5.setBackgroundResource(draw[newarr[4]]);
        }
    }
}

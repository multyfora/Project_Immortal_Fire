package com.example.project_immortal_fire;


import java.util.Arrays;

public abstract class Cards {
    public final static String[] AllCards = new String[]{"salamander_fire_card01", "phoenix_fire_card02", "dragon_fire_card03", "kirin_fire_card04", "lion_fire_card05", "koi_fish_water_card06", "leviathan_water_card07", "shark_water_card08", "09", "10", "raiden_electro_card11", "mjolnir_electro_card12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", };
    public final static int[] ATK = new int[]{-1,12,10,20,11,13,5,23,12,-1,-1,21,15};
    public final static int[] HP = new int[]{-1,10,11,20,11,15,30,25,13,-1,-1,18,15};
    public static int getHP(String CardName){
        int key = Integer.parseInt(CardName.charAt(CardName.length() - 2)+String.valueOf(CardName.charAt(CardName.length() - 1)));
        return Arrays.binarySearch(HP,key);
    }
    public static int getATK(String CardName){
        int key = Integer.parseInt(CardName.charAt(CardName.length() - 2)+String.valueOf(CardName.charAt(CardName.length() - 1)));
        return Arrays.binarySearch(ATK,key);
    }

}


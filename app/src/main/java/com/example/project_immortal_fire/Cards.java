package com.example.project_immortal_fire;

//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗
//UNFINISHEEEEDDD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!🚫🚫🚫🚫🚫❌❌❗❗❗❗❗❗











public abstract class Cards {
    public final static String[] AllCards = new String[]{"salamander_fire_card01", "phoenix_fire_card02", "dragon_fire_card03", "kirin_fire_card04", "lion_fire_card05", "koi_fish_water_card06", "leviathan_water_card07", "shark_water_card08", "09", "10", "raiden_electro_card11", "mjolnir_electro_card12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", };
    private String _ElementalType;
    private int _HP;
    private int _DMG;
    private int _Mana;
    protected Cards(int HP, int DMG, int Mana,String ElementalType){
        _HP = HP;
        _DMG = DMG;
        _Mana = Mana;
        _ElementalType = ElementalType;
    }
}
class Card1 extends Cards{
    public Card1(){
        super(2,3,5,"Fire");
    }
    public static int getHP(){
        return 2;
    }
    public static int getDMG(){
        return 3;
    }
    public static int getMana(){
        return 5;
    }
    public static String getElementalType(){
        return "Fire";
    }
}
class Card2 extends Cards{
    public Card2(){
        super(3,2,3,"Wind");
    }
    public static int getHP(){
        return 3;
    }
    public static int getDMG(){
        return 2;
    }
    public static int getMana(){
        return 3;
    }
    public static String getElementalType(){
        return "Wind";
    }
}
class Card3 extends Cards{
    public Card3(){
        super(6,1,4,"Water");
    }
    public static int getHP(){
        return 6;
    }
    public static int getDMG(){
        return 1;
    }
    public static int getMana(){
        return 4;
    }
    public static String getElementalType(){
        return "Water";
    }
}
class Card4 extends Cards{
    public Card4(){
        super(4,4,6,"Electricity");
    }
    public static int getHP(){
        return 4;
    }
    public static int getDMG(){
        return 4;
    }
    public static int getMana(){
        return 6;
    }
    public static String getElementalType(){
        return "Electricity";
    }
}
class Card5 extends Cards{
    public Card5(){
        super(7,7,10,"Darkness");
    }
    public static int getHP(){
        return 7;
    }
    public static int getDMG(){
        return 7;
    }
    public static int getMana(){
        return 10;
    }
    public static String getElementalType(){
        return "Darkness";
    }
}



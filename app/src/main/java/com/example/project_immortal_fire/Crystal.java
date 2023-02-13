package com.example.project_immortal_fire;

public class Crystal {

    private int hp = 100;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void dmg(int dmg) {
        hp -= dmg;
    }
}

package Characters;

import Houses.Gryffindor;
import Houses.House;
import Houses.Hufflepuff;
import Houses.Slytherin;
import Spells.Spell;

public abstract class Character {
    double HP;
    boolean alive;
    double positionX;
    double positionY;
    double positionZ;
    double maxHP;

    public abstract void attack(Character victim) ;

    public void die() {
        alive = false;
    }

    public void damage(double damage) {
        if (HP <= damage) { die();}
        else { HP = HP - damage; }
    }

    public void spawn(double positionX, double positionY, double positionZ) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        alive = true;
        HP = maxHP;
    }

    public double getHP() {
        return HP;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract String getName();
}

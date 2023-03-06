package Characters;

import Houses.Gryffindor;
import Houses.House;
import Houses.Hufflepuff;
import Houses.Slytherin;
import Spells.Spell;

public abstract class Character {
    final double MAX_HP = 0;
    double HP;
    boolean alive;

    public abstract void attack(Character victim) ;

    public void die() {
        alive = false;
    }

    public void damage(double damage) {
        if (HP <= damage) { die();}
        else { HP = HP - damage; }
    }
}

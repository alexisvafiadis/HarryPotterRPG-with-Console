package Characters;

import Houses.Gryffindor;
import Houses.House;
import Houses.Hufflepuff;
import Houses.Slytherin;

public abstract class Character {
    final double MAX_HP = 0;
    double HP;

    public void attack(Character victim) {
        double damage = 0;
        if (victim instanceof Wizard) {
            Wizard wizard_victim = (Wizard) victim;
            House victim_house = wizard_victim.getHouse();
            if (victim_house instanceof Gryffindor) {
                damage = damage * ((Gryffindor) victim_house).getDamageResistance();
            }
        }
        if (this instanceof Wizard) {
            Wizard wizard = (Wizard) victim;
            House house = wizard.getHouse();
            if (house instanceof Slytherin) {
                damage = damage * ((Slytherin) house).getDamageMultiplier();
            }
        }
        if (victim.HP <= damage) {
            victim.die();
        }
        else {
            victim.damage(damage);
        }
    }

    public abstract void die();

    public void damage(double damage) {
        HP = HP - damage;
    }
}

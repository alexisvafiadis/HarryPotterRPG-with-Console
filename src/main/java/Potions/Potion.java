package Potions;

import Characters.Wizard;

public class Potion {
    double HP_RESTORE;
    Wizard owner;

    public Potion(Wizard wizard) {
        this.owner = wizard;
        this.HP_RESTORE = HP_RESTORE;
    }

    public void use() {
        owner.heal(HP_RESTORE);
    }
}

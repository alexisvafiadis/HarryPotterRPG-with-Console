package Potions;

import Characters.Wizard;

public class Potion {
    double effectValue;
    Wizard owner;
    String name;
    Effect effect;

    public Potion(Wizard wizard, String name, Effect effect, double effectValue, double duration) {
        owner = wizard;
        this.name = name;
        this.effect = effect;
        this.effectValue = effectValue;
    }

    public void use() {
        switch (effect) {
            case HEAL:
                owner.heal(effectValue);
                break;
        }
    }

    public String getName() {
        return name;
    }

    public Effect getEffect() {
        return effect;
    }
}

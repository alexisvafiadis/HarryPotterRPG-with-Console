package Wands;

import Characters.Wizard;
import Game.Game;
import Spells.Spell;

import static java.lang.Math.random;

public class Wand {
    Core core;
    Wood wood;
    int size;
    String name;
    Wizard owner;
    int nbOfUses;

    public Wand(Core core, Wood wood, int size, Wizard owner) {
        this.core = core;
        this.size = size;
        this.wood = wood;
        this.owner = owner;
        nbOfUses = 0;
    }

    /*
    public boolean cast(Spell spell, Character victim) {
        if (random() < owner.getAccuracy() * spell.getMasteryScore()) {
        }
        else {

        }
    }
     */
}

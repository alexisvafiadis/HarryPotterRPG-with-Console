package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public abstract class SimpleSpell extends Spell {
    //spell that just needs a Character target as an argument

    public SimpleSpell(Game game, Wizard wizard, String name, int range, float cooldown, double learningExponent, double defaultMasteryScore) {
        super(game, wizard, name, range, cooldown, learningExponent, defaultMasteryScore);
    }

    public abstract void cast(Character target);
}

package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Potions.ActiveEffect;
import Potions.EffectType;

public class Rictumsempra extends SimpleSpell{
    final int EFFECT_DURATION = 3;

    public Rictumsempra(Game game, Wizard wizard) {
        super(game, wizard, "Rictumsempra", 5, 1, 0.4, 0.6);
    }

    public void cast(Character target) {
        if (isCastSuccessful(target)) {
            //check if target is human
            target.giveEffect(EffectType.LAUGH, new ActiveEffect(EFFECT_DURATION, 0.6));
            displayCastMessage(target.getName() + " is " + EffectType.LAUGH.getStartMessage());
        }
    }

}

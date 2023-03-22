package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Potions.ActiveEffect;
import Potions.EffectType;

public class Tarantallegra extends Spell{
    final int EFFECT_DURATION = 4;

    public Tarantallegra(Game game, Wizard wizard) {
        super(game, wizard, "Tarantallegra", 5, 1, 0.4, 0.6);
    }

    public void cast(Character target) {
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.CONFUSION, new ActiveEffect(EFFECT_DURATION, 0.6));
            getDisplay().announceSuccess(target.getName() + " is " + EffectType.CONFUSION.getStartMessage());
        }
    }
}

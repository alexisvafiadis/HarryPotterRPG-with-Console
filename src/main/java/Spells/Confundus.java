package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Potions.ActiveEffect;
import Potions.EffectType;

public class Confundus extends Spell{
    final int EFFECT_DURATION = 4;

    public Confundus(Game game, Wizard wizard) {
        super(game, wizard, "Confundus", 5, 1, 0.4, 0.5);
    }

    public void cast(Character target) {
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.CONFUSION, new ActiveEffect(EFFECT_DURATION, 0.6));
            getDisplay().announceSuccess(target.getName() + " is " + EffectType.CONFUSION.getStartMessage());
        }
    }

    @Override
    public void tryForFirstTime() {

    }
}

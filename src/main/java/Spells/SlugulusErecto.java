package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Potions.ActiveEffect;
import Potions.EffectType;

public class SlugulusErecto extends SimpleSpell{
    final int EFFECT_DURATION = 3;

    public SlugulusErecto(Game game, Wizard wizard) {
        super(game, wizard, "Slugulus Erecto", 5, 1, 0.35, 0.55);
    }

    public void cast(Character target) {
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.SLUG_VOMITING, new ActiveEffect(EFFECT_DURATION, 0.72));
            displayCastMessage(target.getName() + " is " + EffectType.CONFUSION.getStartMessage());
        }
    }
}

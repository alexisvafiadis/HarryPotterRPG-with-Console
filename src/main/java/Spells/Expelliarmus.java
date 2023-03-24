package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Potions.ActiveEffect;
import Potions.EffectType;

public class Expelliarmus extends SimpleSpell{
    private final int EFFECT_DURATION = 3;

    public Expelliarmus(Game game, Character wizard) {
        super(game, wizard, "Expelliarmus", 5, 1, 0.28, 0.45);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.giveEffect(EffectType.DISARM, new ActiveEffect(EFFECT_DURATION, 1));
            displayCastMessage("disarmed " + target.getName());
        }
    }


}

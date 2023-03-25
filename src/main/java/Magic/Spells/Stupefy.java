package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class Stupefy extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Stupefy(Game game, Character wizard) {
        super(game, wizard, "Stupefy", 5, 1, 0.4, 0.);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.STUN, new ActiveEffect(EFFECT_DURATION, 0.7));
            displayCastMessage("");
        }
    }

}

package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class Protego extends SimpleSpell {
    private final int EFFECT_DURATION = 2;

    public Protego(Game game, Character wizard) {
        super(game, wizard, "Confundus", 5, 1, 0.4, 0.);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.SHIELD, new ActiveEffect(EFFECT_DURATION, 0.85));
            displayCastMessage("create a shimmering, transparent shield that can block attacks");
        }
    }

}

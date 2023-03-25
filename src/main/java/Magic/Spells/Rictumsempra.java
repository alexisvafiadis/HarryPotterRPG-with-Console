package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class Rictumsempra extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Rictumsempra(Game game, Character wizard) {
        super(game, wizard, "Rictumsempra", 5, 1, 0.4, 0.6);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            //check if target is human
            target.giveEffect(EffectType.LAUGH, new ActiveEffect(EFFECT_DURATION, 0.6));
            displayCastMessage("");
        }
    }

}

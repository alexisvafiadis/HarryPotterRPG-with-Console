package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class Tarantallegra extends SimpleSpell {
    private final int EFFECT_DURATION = 4;

    public Tarantallegra(Game game, Character wizard) {
        super(game, wizard, "Tarantallegra", 5, 1, 0.4, 0.6);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.DANCING, new ActiveEffect(EFFECT_DURATION, 0.6));
            displayCastMessage("");
        }
    }
}

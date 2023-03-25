package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class SlugulusErecto extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public SlugulusErecto(Game game, Character wizard) {
        super(game, wizard, "Slugulus Erecto", 5, 1, 0.35, 0.55);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(getWizard())) {
            target.giveEffect(EffectType.SLUG_VOMITING, new ActiveEffect(EFFECT_DURATION, 0.72));
            displayCastMessage("");
        }
    }
}

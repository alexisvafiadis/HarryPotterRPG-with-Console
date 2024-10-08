package Core.Magic.Spells;

import Core.Characters.Character;
import Core.Game.Game;
import Core.Magic.ActiveEffect;
import Core.Magic.EffectType;
import Core.Magic.SimpleSpell;

public class Imperius extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Imperius(Game game, Character wizard) {
        super(game, wizard, "Imperius", 5, 1, 0.21, 0.12);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.giveEffect(EffectType.UNDER_CONTROL, new ActiveEffect(EFFECT_DURATION, 0.7));
            displayCastMessage(target.getName() + "'s eyes glaze over");
        }
    }

    @Override
    public void displayInstructions() {

    }
}

package Core.Magic.Spells;

import Core.Characters.Character;
import Core.Game.Game;
import Core.Magic.ActiveEffect;
import Core.Magic.EffectType;
import Core.Magic.SimpleSpell;

public class Fiendfyre extends SimpleSpell {
    private final double DAMAGE = 30;
    private final int EFFECT_DURATION = 3;

    public Fiendfyre(Game game, Character wizard) {
        super(game, wizard, "Fiendfyre", 5, 1, 0.19, 0.15);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DAMAGE));
            displayCastMessage("the cursed flames erupted, consuming everything in their path with an inferno of heat and destruction.");
            target.giveEffect(EffectType.BURN, new ActiveEffect(EFFECT_DURATION, calculateDamage(DAMAGE)));
        }
    }

    @Override
    public void displayInstructions() {

    }
}

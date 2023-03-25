package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class Crucio extends SimpleSpell {
    private final double DAMAGE_AT_THE_END = 50;
    private final int EFFECT_DURATION = 3;

    public Crucio(Game game, Character wizard) {
        super(game, wizard, "Crucio", 5, 1, 0.25, 0.4);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            displayCastMessage("a burst of bright blue light emerges, striking " + target.getName());
            target.giveEffect(EffectType.LAUGH, new ActiveEffect(EFFECT_DURATION, calculateDamage(DAMAGE_AT_THE_END)));
        }
    }
}

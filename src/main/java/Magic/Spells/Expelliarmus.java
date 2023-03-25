package Magic.Spells;

import Characters.Character;
import Characters.EnemyWizard;
import Game.Game;
import Magic.ActiveEffect;
import Magic.EffectType;
import Magic.SimpleSpell;

public class Expelliarmus extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Expelliarmus(Game game, Character wizard) {
        super(game, wizard, "Expelliarmus", 5, 1, 0.28, 0.45);
    }

    public void cast(Character target) {
        if (!target.hasWeapon() && !(target instanceof EnemyWizard)) {
            display.announceFail(target.getName() + " doesn't have a weapon");
        }
        else {
            use();
            if (isCastSuccessful(target)) {
                target.giveEffect(EffectType.DISARM, new ActiveEffect(EFFECT_DURATION, 1));
                displayCastMessage("");
            }
        }
    }


}

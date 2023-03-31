package Core.Magic.Spells;

import Core.Characters.Character;
import Core.Characters.EnemyWizard;
import Core.Game.Game;
import Core.Magic.ActiveEffect;
import Core.Magic.EffectType;
import Core.Magic.SimpleSpell;

public class Expelliarmus extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Expelliarmus(Game game, Character wizard) {
        super(game, wizard, "Expelliarmus", 5, 1, 0.66, 0.29);
    }

    public void cast(Character target) {
        if (!target.hasWeapon() && !(target instanceof EnemyWizard)) {
            display.announceFail(target.getName() + " doesn't have a weapon");
        }
        else {
            use();
            if (isCastSuccessful(target)) {
                target.giveEffect(EffectType.DISARM, new ActiveEffect(EFFECT_DURATION, 1));
                displayCastMessage(null);
            }
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForYes("To cast this spell, point your wand at your opponent and say \"Expelliarmus\" clearly.\n" +
                "If the spell is successful, your opponent's weapon should be knocked out of their hand.\n" +
                "This spell can be useful for disarming opponents and leaving them vulnerable to attack.\n"
                + "Understood?");
    }
}

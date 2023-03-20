package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public class Sectumsempra extends Spell{
    double DEFAULT_DAMAGE = 40;

    public Sectumsempra(Game game, Wizard wizard) {
        super(game, wizard, "Sectumsempra", 5, 1, 0.32, 0.58);
    }

    public void cast(Character target) {
        if (isCastSuccessful(getWizard(), target)) {
            target.damage(wizard.amplifySpellDamage(DEFAULT_DAMAGE));
            getDisplay().announceSuccess("You have successfully lacerated " + target.getName() + " and caused severe haemorrhaging");
        }
    }

    @Override
    public void tryForFirstTime() {

    }
}

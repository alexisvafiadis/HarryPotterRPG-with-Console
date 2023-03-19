package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public class Expelliarmus extends Spell{

    public Expelliarmus(Game game, Wizard wizard) {
        super(game, wizard, "Expelliarmus", 5, 1, 0.35, 0.55);
    }

    public void cast(Character target) {
        if (isCastSuccessful(getWizard(), target)) {
            getDisplay().announceSuccess("You have successfully thrown away " + target.getName() + " 's weapon!");
        }
    }

    @Override
    public void tryForFirstTime() {

    }
}

package Spells;

import Characters.Wizard;
import Game.Game;

public class Expectopatronum extends Spell{

    public Expectopatronum(Game game, Wizard wizard) {
        super(game, wizard, "Expecto Patronum", 5, 1, 0.25, 0.3);
    }

    public boolean cast() {
        if (isCastSuccessful(getWizard())) {
            getDisplay().announceSuccess("You have successfully summoned a protector!");
            return true;
        }
        return false;
    }

    @Override
    public void tryForFirstTime() {

    }
}

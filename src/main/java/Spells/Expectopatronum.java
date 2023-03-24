package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public class Expectopatronum extends Spell{

    public Expectopatronum(Game game, Wizard wizard) {
        super(game, wizard, "Expecto Patronum", 5, 1, 0.15, 0.08);
    }

    public boolean cast() {
        use();
        boolean isCastSuccessful = isCastSuccessful(getWizard());
        if (isCastSuccessful) {
            displayCastMessage("summoned a Patronus to repel the Dementors.");
        }
        return (isCastSuccessful);
    }

}

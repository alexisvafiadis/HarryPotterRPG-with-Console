package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public class Expelliarmus extends SimpleSpell{

    public Expelliarmus(Game game, Wizard wizard) {
        super(game, wizard, "Expelliarmus", 5, 1, 0.35, 0.55);
    }

    public void cast(Character target) {
        if (isCastSuccessful(target)) {
            displayCastMessage("thrown away " + target.getName() + "'s weapon");
        }
    }


}

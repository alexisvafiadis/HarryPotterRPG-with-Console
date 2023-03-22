package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public class Lumos extends Spell{

    public Lumos(Game game, Wizard wizard) {
        super(game, wizard, "Lumos", 5, 1, 0.32, 0.58);
    }

    public void cast(Character target) {
        if (isCastSuccessful(getWizard(), target)) {
            getDisplay().announceSuccess("You have successfully lacerated " + target.getName() + " and caused severe haemorrhaging");
        }
    }
}

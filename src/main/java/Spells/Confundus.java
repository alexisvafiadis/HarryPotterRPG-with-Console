package Spells;

import Characters.Character;
import Characters.Wizard;
import Extras.Item;
import Game.Game;

public class Confundus extends Spell{
    final double CONFUSION_DURATION = 4;

    public Confundus(Game game, Wizard wizard) {
        super(game, wizard, "Confundus", 5, 10, 0.55, 0.68);
    }

    public void cast(Character victim) {
        if (isCastSuccessful(getWizard())) {
            game.getDisplay().announceSuccess("You have confused " + victim.getName());
            victim.setConfused(true);
        }
    }

    @Override
    public void tryForFirstTime() {

    }
}

package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;

public class Confundus extends Spell{
    final double CONFUSION_DURATION = 4;

    public Confundus(Game game, Wizard wizard) {
        super(game, wizard, "Confundus", 5, 1, 0.4, 0.5);
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

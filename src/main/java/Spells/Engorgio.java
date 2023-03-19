package Spells;

import Characters.Wizard;
import Items.Item;
import Game.Game;

public class Engorgio extends Spell{
    final double ENLARGE_COEFFICIENT = 2.25;

    public Engorgio(Game game, Wizard wizard) {
        super(game, wizard, "Engorgio", 5, 1, 0.55, 0.68);
    }

    public void cast(Item item) {
        if (isCastSuccessful(getWizard())) {
            getDisplay().announceSuccess("You have enlarged this " + item.getItemType().toString());
            item.getItemType().enlarge(ENLARGE_COEFFICIENT);
        }
    }

    @Override
    public void tryForFirstTime() {

    }
}

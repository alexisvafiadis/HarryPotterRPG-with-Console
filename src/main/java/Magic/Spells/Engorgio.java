package Magic.Spells;

import Characters.Wizard;
import Items.Item;
import Game.Game;
import Magic.Spell;

public class Engorgio extends ItemSpell {
    //WILL NEED TO IMPLEMENT A MAXIMUM SIZE THAT THE ITEM CAN REACH OTHERWISE THE SPELL IS OVERPOWERED
    private final double ENLARGE_COEFFICIENT = 2.25;

    public Engorgio(Game game, Wizard wizard) {
        super(game, wizard, "Engorgio", 5, 1, 0.55, 0.68,"to engorge");
    }

    public void cast(Item item) {
        use();
        if (isCastSuccessful(getWizard())) {
            displayCastMessage("enlarged this " + item.getItemType().toString());
            item.getItemType().enlarge(ENLARGE_COEFFICIENT);
        }
    }

}

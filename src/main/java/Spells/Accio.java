package Spells;

import Characters.Wizard;
import Items.Item;
import Game.Game;
import Items.Weapon;

public class Accio extends Spell{
    private final double CAST_CHANCE_MULTIPLIER = 5;

    public Accio(Game game, Wizard wizard) {
        super(game, wizard, "Accio", 5, 1, 0.4, 0.56);
    }

    public void cast(Weapon weapon) {
        use();
        if (isCastSuccessful(wizard)) {
            wizard.setWeapon(weapon);
            displayCastMessage("summoned a " + weapon.toString());
        }
    }

    public boolean cast(Item item) {
        use();
        boolean castSuccessful = isCastSuccessful(wizard);
        if (castSuccessful) {
            if (Math.random() < CAST_CHANCE_MULTIPLIER / wizard.getMap().calculateDistance(wizard,item)) {
                displayCastMessage("summoned a " + item.getItemType().toString());
            }
            else {
                display.announceFail("Accio failed. You might be too far.");
            }
        }
        return castSuccessful;
    }


}

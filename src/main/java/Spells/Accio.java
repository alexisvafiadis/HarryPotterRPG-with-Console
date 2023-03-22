package Spells;

import Characters.Wizard;
import Items.Item;
import Game.Game;
import Items.Weapon;

public class Accio extends Spell{
    final double CAST_CHANCE_MULTIPLIER = 5;

    public Accio(Game game, Wizard wizard) {
        super(game, wizard, "Accio", 5, 1, 0.4, 0.56);
    }

    public void cast(Weapon weapon) {
        if (isCastSuccessful(wizard)) {
            wizard.setWeapon(weapon);
            display.announceSuccess("You have successfuly summoned a " + weapon.toString());
        }
    }

    public boolean cast(Item item) {
        boolean castSuccessful = isCastSuccessful(wizard);
        if (castSuccessful) {
            if (Math.random() < CAST_CHANCE_MULTIPLIER / wizard.getMap().calculateDistance(wizard,item)) {
                display.announceSuccess("You have successfuly summoned a " + item.getItemType().toString());
            }
            else {
                display.announceFail("Accio failed. You might be too far.");
            }
        }
        return castSuccessful;
    }


}

package Spells;

import Characters.Wizard;
import Items.Item;
import Game.Game;
import Items.Weapon;

public class Accio extends Spell{
    final double CAST_CHANCE_MULTIPLIER = 5;

    public Accio(Game game, Wizard wizard) {
        super(game, wizard, "Accio", 5, 1, 0.5, 0.56);
    }

    public void cast(Weapon weapon) {
        if (isCastSuccessful(wizard)) {
            wizard.setWeapon(weapon);
            display.announceSuccess("You have successfuly summoned a " + weapon.toString());
        }
    }

    public void cast(Item item) {
        if (isCastSuccessful(getWizard())) {
            double distance = Math.sqrt(Math.pow((item.getPositionX() - wizard.getPositionX()), 2) + Math.pow((item.getPositionY() - wizard.getPositionY()), 2) + Math.pow((item.getPositionZ() - wizard.getPositionZ()), 2));
            if (Math.random() < CAST_CHANCE_MULTIPLIER / distance) {
                display.announceSuccess("You have successfuly summoned a " + item.getItemType().toString());
            }
            else {
                display.announceFail("Accio failed. You might be too far.");
            }
        }
    }

    @Override
    public void tryForFirstTime() {

    }


}

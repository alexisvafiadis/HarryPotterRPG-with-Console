package Spells;

import Characters.Wizard;
import Items.Item;
import Game.Game;
import Items.Weapon;

public class Accio extends Spell{

    public Accio(Game game, Wizard wizard) {
        super(game, wizard, "Accio", 5, 1, 0.5, 0.56);
    }

    public void cast(Weapon weapon) {
        if (isCastSuccessful(getWizard())) {
            getWizard().setWeapon(weapon);
            getDisplay().announceSuccess("You have successfuly summoned a " + weapon.toString());
        }
    }

    @Override
    public void tryForFirstTime() {

    }


}

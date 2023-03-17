package Spells;

import Characters.Character;
import Characters.Wizard;
import Extras.Item;
import Game.Game;

public class WingardiumLeviosa extends Spell{
    double defaultDamage = 15;

    public WingardiumLeviosa(Game game, Wizard wizard) {
        super(game, wizard, "Wingardium Leviosa", 5, 10, 0.3, 0.5);
    }

    public void cast(Item item, Character target) {
        if (isCastSuccessful(getWizard())) {
            game.announceSuccess("You have used Wingardium Leviosa to throw this " + item.getItemType().toString() + " on " + target.toString());
            target.damage(wizard.amplifyDamage(defaultDamage * item.getDamageMultiplier()));
        }
    }
}

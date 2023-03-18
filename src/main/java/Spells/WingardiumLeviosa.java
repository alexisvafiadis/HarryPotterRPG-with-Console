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
            game.getDisplay().announceSuccess("You have used Wingardium Leviosa to throw this " + item.getItemType().toString() + " on " + target.getName());
            target.damage(wizard.amplifyDamage(defaultDamage * item.getDamageMultiplier()));
        }
    }

    @Override
    public void tryForFirstTime() {
        getGame().getInputParser().waitForYes("Aim your wand at the object you want to levitate and concentrate on it. Visualize the object lifting up off the ground and floating in the air.\n" +
                "As you concentrate on the object, say the incantation \"Wingardium Leviosa\" and make the wand movement. If you did it correctly, the object should start to lift off the ground.\n" +
                "Once the object is floating in the air, you can move it around by pointing your wand in different directions. Try moving the object to the left, right, up, and down by changing the direction of your wand.\n" +
                "To release the object, simply stop concentrating on it and let it fall back to the ground.\n"
                + "Understood?");
    }
}

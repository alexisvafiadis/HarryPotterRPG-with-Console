package Core.Magic.Spells;

import Core.Characters.Character;
import Core.Characters.Wizard;
import Core.Game.Game;
import Core.Items.Item;

public class Reducto extends ItemSpell {
    private final double DEFAULT_DAMAGE = 25;

    public Reducto(Game game, Wizard wizard) {
        super(game, wizard, "Reducto", 5, 1, 0.48, 0.26,"to explode");
    }

    public boolean cast(Item item, Character target) {
        use();
        if (isCastSuccessful(target)) {
            displayCastMessage(item.getItemType().toString() + " exploded into countless pieces, sending debris flying everywhere, and hurting " + target.getName());
            target.damage(calculateDamage(DEFAULT_DAMAGE * item.getItemType().getDamageMultiplier()));
            return true;
        }
        return false;
    }

    public void displayInstructions() {
        inputParser.waitForYes("Aim your wand at the object you want to destroy.\n" +
                " Say \"Reducto\" with confidence and flick your wand in a quick diagonal motion\n" +
                "A powerful red blast of light will shoot from your wand and hit the target, causing it to explode or shatter.\n" +
                "Understood?");
    }
}

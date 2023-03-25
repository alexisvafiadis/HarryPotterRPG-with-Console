package Magic.Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Items.Item;
import Magic.Spell;

public class Reducto extends ItemSpell {
    private final double DEFAULT_DAMAGE = 25;

    public Reducto(Game game, Wizard wizard) {
        super(game, wizard, "Reducto", 5, 1, 0.3, 0.5,"to explode");
    }

    public boolean cast(Item item, Character target) {
        use();
        if (isCastSuccessful(getWizard())) {
            displayCastMessage(item.getItemType().toString() + " explodes into countless pieces, sending debris flying everywhere, and hurting " + target.getName());
            target.damage(calculateDamage(DEFAULT_DAMAGE * item.getItemType().getDamageMultiplier()));
            return true;
        }
        return false;
    }
}

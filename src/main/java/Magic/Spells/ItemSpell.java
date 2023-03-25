package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.Spell;

public class ItemSpell extends Spell {
    private String stringForItem;

    public ItemSpell(Game game, Character wizard, String name, int range, float cooldown, double learningExponent, double defaultMasteryScore, String stringForItem) {
        super(game, wizard, name, range, cooldown, learningExponent, defaultMasteryScore);
        this.stringForItem = stringForItem;
    }

    public String getStringForItem() {
        return stringForItem;
    }
}

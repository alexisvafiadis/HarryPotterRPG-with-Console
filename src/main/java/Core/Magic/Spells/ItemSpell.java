package Core.Magic.Spells;

import Core.Characters.Character;
import Core.Game.Game;
import Core.Magic.Spell;

public abstract class ItemSpell extends Spell {
    private String stringForItem;

    public ItemSpell(Game game, Character wizard, String name, int range, float cooldown, double learningExponent, double defaultMasteryScore, String stringForItem) {
        super(game, wizard, name, range, cooldown, learningExponent, defaultMasteryScore);
        this.stringForItem = stringForItem;
    }

    public String getStringForItem() {
        return stringForItem;
    }
}

package Characters;

import Game.Game;
import Items.Weapon;
import Spells.Spell;

import java.util.HashMap;

public abstract class EnemyWizard extends AbstractEnemy{
    HashMap<String, Spell> knownSpells;

    public EnemyWizard(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep, int attackDelay) {
        super(game, maxHP, physicalDamage, vulnerabilityToMagic, weapon, charTile, moveStep, attackDelay);
    }
}

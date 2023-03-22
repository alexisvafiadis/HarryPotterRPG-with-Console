package Characters;

import Game.Game;
import Items.Weapon;

public abstract class AbstractEnemy extends Character {
    int attackDelay;

    public AbstractEnemy(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep, int attackDelay) {
        super(game, maxHP, physicalDamage, vulnerabilityToMagic, weapon, charTile, moveStep);
        this.attackDelay = attackDelay;
    }

    public abstract void act();

    public int getAttackDelay() {
        return attackDelay;
    }
}

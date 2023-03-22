package Characters;

import Game.Game;
import Items.Weapon;

public abstract class Boss extends AbstractEnemy {

    public Boss(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep, int attackDelay) {
        super(game, maxHP, physicalDamage, vulnerabilityToMagic, weapon, charTile, moveStep, attackDelay);
    }

    @Override
    public void attackedByExpelliarmus() {
        setWeapon(null);
    }

}

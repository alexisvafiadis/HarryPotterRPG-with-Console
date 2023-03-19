package Characters;

import Items.Weapon;

public abstract class Boss extends AbstractEnemy {

    @Override
    public void attackedByExpelliarmus() {
        setWeapon(null);
    }

}

package Characters;

import Extras.Weapon;

public abstract class Boss extends AbstractEnemy {

    @Override
    public void attackedByExpelliarmus() {
        setWeapon(null);
    }

    @Override
    public void attack(Character victim) {
        if (canAttack()) {
            Weapon weapon = getWeapon();
            if (weapon != null) {
                victim.damage(getPhysicalDamage() + weapon.getAttackDamage());
            } else {
                victim.damage(getPhysicalDamage());
            }
            getGame().getDisplay().displayInfo("You have been attacked by " + getName());
        }
    }
}

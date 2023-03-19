package Characters;

import Items.Item;
import Items.Weapon;
import Game.Game;

public class Troll extends Boss{
    double positionX;
    double positionY;
    double positionZ;

    public Troll(Game game) {
        setGame(game);
        setMaxHP(300);
        setPhysicalDamage(14);
        setWeapon(Weapon.CLUB);
        setAttackDelay(2);
        setVulnerabilityToMagic(1);
        spawn(positionX, positionY, positionZ);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public boolean getsHitBy(Item item) {
        return (item.getPositionX() == positionX && item.getPositionY() == positionY && item.getPositionZ() == positionZ);
    }

    public String getName() {
        return "The Troll";
    }
}

package Characters;

import Items.Item;
import Game.Game;

public class Basilisk extends Boss{
    double positionX;
    double positionY;
    double positionZ;

    public Basilisk(Game game) {
        setGame(game);
        setMaxHP(80);
        setPhysicalDamage(35);
        setAttackDelay(1);
        setVulnerabilityToMagic(1);
        setWeapon(null);
        spawn(positionX, positionY, positionZ);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public boolean getsHitBy(Item item) {
        return (item.getPositionX() == positionX && item.getPositionY() == positionY && item.getPositionZ() == positionZ);
    }

    public String getName() {
        return "The Basilisk";
    }
}

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
        setPhysicalDamage(25);
        setAttackDelay(1);
        setVulnerabilityToMagic(1);
        setWeapon(null);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "The Basilisk";
    }
}

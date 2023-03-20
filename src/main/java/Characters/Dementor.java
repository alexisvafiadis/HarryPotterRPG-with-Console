package Characters;

import Items.Item;
import Game.Game;

public class Dementor extends Boss{
    double positionX;
    double positionY;
    double positionZ;

    public Dementor(Game game) {
        setGame(game);
        setMaxHP(40);
        setPhysicalDamage(12);
        setAttackDelay(1);
        setVulnerabilityToMagic(1);
        setWeapon(null);
        spawn(positionX, positionY, positionZ);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "a Dementor";
    }
}

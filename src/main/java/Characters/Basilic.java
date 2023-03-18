package Characters;

import Extras.Item;
import Extras.Weapon;
import Game.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Basilic extends Boss{
    double positionX;
    double positionY;
    double positionZ;

    public Basilic(Game game) {
        setGame(game);
        setMaxHP(250);
        setPhysicalDamage(12);
        setAttackDelay(1);
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
        return "The Troll";
    }
}

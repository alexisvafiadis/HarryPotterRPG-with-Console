package Characters;

import Extras.Item;
import Extras.Weapon;
import Game.Game;

public class Troll extends Boss{
    double positionX;
    double positionY;
    double positionZ;

    public Troll(Game game) {
        setGame(game);
        setMaxHP(300);
        setWeapon(Weapon.CLUB);
        setAttackDelay(2);
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

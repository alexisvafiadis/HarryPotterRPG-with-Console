package Characters;

import Items.Item;
import Items.Weapon;
import Game.Game;

public class DeathEater extends Boss{

    public DeathEater(Game game) {
        setGame(game);
        setMaxHP(80);
        setPhysicalDamage(30);
        setWeapon(null);
        setAttackDelay(1);
        setVulnerabilityToMagic(0.9);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "The Troll";
    }
}

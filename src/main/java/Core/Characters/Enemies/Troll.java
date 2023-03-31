package Core.Characters.Enemies;

import Core.Characters.Boss;
import Core.Items.Item;
import Core.Items.Weapon;
import Core.Game.Game;

public class Troll extends Boss {
    public Troll(Game game) {
        super(game, 100, 16, 1, Weapon.CLUB,'T',1,2);
    }

    public void act() {
        attack(game.getPlayer());
    }

    public boolean getsHitBy(Item item) {
        return (item.getPositionX() == positionX && item.getPositionY() == positionY);
    }

    public String getName() {
        return "The Troll";
    }
}

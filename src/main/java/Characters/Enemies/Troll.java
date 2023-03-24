package Characters.Enemies;

import Characters.Boss;
import Items.Item;
import Items.Weapon;
import Game.Game;

public class Troll extends Boss {
    public Troll(Game game) {
        super(game, 1, 16, 1, Weapon.CLUB,'T',1,2);
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

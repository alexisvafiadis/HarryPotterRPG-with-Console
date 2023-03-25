package Characters.Enemies;

import Characters.AbstractEnemy;
import Items.Item;
import Items.Weapon;
import Game.Game;

public class DeathEater extends AbstractEnemy {

    public DeathEater(Game game) {
        super(game, 30, 80, 0.9,null,'D',1,1);
    }

    public void act() {
        attack(game.getPlayer());
    }

    public String getName() {
        return "Death Eater";
    }
}

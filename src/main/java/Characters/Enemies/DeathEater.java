package Characters.Enemies;

import Characters.AbstractEnemy;
import Items.Item;
import Items.Weapon;
import Game.Game;

public class DeathEater extends AbstractEnemy {

    public DeathEater(Game game) {
        super(game, 30.0, 80.0, 0.9,null,'D',1,1);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "The Troll";
    }

    @Override
    public void attackedByExpelliarmus() {

    }
}

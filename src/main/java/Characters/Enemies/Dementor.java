package Characters.Enemies;

import Characters.AbstractEnemy;
import Items.Item;
import Game.Game;

public class Dementor extends AbstractEnemy {

    public Dementor(Game game) {
        super(game, 40, 15, 1,null,'D',1,1);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "a Dementor";
    }

    @Override
    public void attackedByExpelliarmus() {

    }
}

package Core.Characters.Enemies;

import Core.Characters.AbstractEnemy;
import Core.Game.Game;

public class Dementor extends AbstractEnemy {

    public Dementor(Game game) {
        super(game, 30, 35, 1,null,'D',1,1);
    }

    public void act() {
        attack(game.getPlayer());
    }

    public String getName() {
        return "a Dementor";
    }

}
package Characters;

import Items.Item;
import Game.Game;

public class Basilisk extends Boss{

    public Basilisk(Game game) {
        super(game, 80.0, 25.0, 1,null,'B',1,1);
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "The Basilisk";
    }
}

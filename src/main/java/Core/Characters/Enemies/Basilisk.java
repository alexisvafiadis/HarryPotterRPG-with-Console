package Core.Characters.Enemies;

import Core.Characters.Boss;
import Core.Game.Game;

public class Basilisk extends Boss {

    public Basilisk(Game game) {
        super(game, 90, 28, 1,null,'B',1,1);
        setCustomBattleStartMessage("Hiss... Sssss...");
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "The Basilisk";
    }
}

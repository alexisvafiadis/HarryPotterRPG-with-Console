package Spells;

import Characters.Character;
import Characters.Wizard;
import Game.Game;
import Levels.Essentials.LevelMap;

public class Lumos extends Spell{

    public Lumos(Game game, Wizard wizard) {
        super(game, wizard, "Lumos", 5, 1, 0.32, 0.58);
    }

    public void cast(Character target, LevelMap map) {
        if (isCastSuccessful(target)) {
            displayCastMessage("");
            map.display();
        }
    }
}

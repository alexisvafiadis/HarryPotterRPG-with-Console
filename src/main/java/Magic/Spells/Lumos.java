package Magic.Spells;

import Characters.Character;
import Game.Game;
import Levels.Essentials.LevelMap;
import Magic.Spell;

public class Lumos extends Spell {

    public Lumos(Game game, Character wizard) {
        super(game, wizard, "Lumos", 5, 1, 0.32, 0.58);
    }

    public void cast(LevelMap map) {
        use();
        if (isCastSuccessful()) {
            displayCastMessage("see your wand light up and illuminate the area around you, revealing new hidden details.");
            display.displayInfo("Map of the Level: ");
            map.display();
        }
    }
}

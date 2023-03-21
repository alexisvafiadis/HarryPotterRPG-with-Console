package Levels;

import Characters.Basilisk;
import Characters.Wizard;
import Game.Game;
import Spells.Lumos;
import Spells.Rictumsempra;

public class Level4 extends Level{
    Wizard player;

    public Level4(Game game) {
        super(game, "The Goblet of Fire","Little Hangleton graveyard", 4, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn(0,0,0);
        super.start();
        //spawn(voldemort)
        //fight(voldemort)
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have gotten away!");
        teachFunnySpells();
        askForUpgrade();

    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("Voldemort and Peter Pettigrew are nearby. You should probably get away.");
        display.displayInfo("You have to get close to the Portkey to attract it to you using Accio then use the Portkey");
        wishGoodLuck();
    }

    public void teachFunnySpells() {
        display.displayInfo("To help you recover, let me teach you some funny spells. Let's tart with Rictumsempra.");
        inputParser.waitForYes("First, make sure you have your wand at the ready and choose your target carefully.\n" +
                "The Rictumsempra spell is a jinx that causes your target to experience a tickling sensation, so it's a harmless spell that wouldn't be useful in a dangerous situation.\n" +
                "Point your wand at your target and clearly enunciate Rictumsempra while flicking your wand in a zig-zag motion.\n" +
                "If successful, your target should start to experience an uncontrollable tickling sensation, causing them to double over with laughter.\n" +
                "Keep in mind that this spell should only be used in a controlled and safe environment. It's important to always consider the consequences of using magic and to use it responsibly.\n");
        player.learnSpell(new Rictumsempra(game, player));
        display.displayInfo("There's also Lumos");
        inputParser.waitForYes("Hold your wand tightly in your hand.\n" +
                "Focus on the tip of your wand and visualize a bright light appearing there.\n" +
                "Say Lumos in a clear and firm voice while pointing your wand upward.\n" +
                "A bright light should appear at the tip of your wand, illuminating the area around you.\n" +
                "To turn off the light, simply say Nox while pointing your wand downward.");
        player.learnSpell(new Lumos(game, player));
    }

}

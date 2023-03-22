package Levels;

import Characters.Dementor;
import Game.Game;
import Levels.Essentials.Battle;
import Spells.Expectopatronum;
import Spells.Lumos;

public class Level3 extends Level{
    boolean expectoPatronumUsed;

    public Level3(Game game) {
        super(game, "The Prisoner of Azkaban","the Great Lake", 3, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn(0,0);
        super.start();
        expectoPatronumUsed = false;
        while (!expectoPatronumUsed) {
            new Battle(game, this, player, new Dementor(game));
        }
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed scared the Dementors away!");
        teachLumos();
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("For this level, you have to scare the Dementors away.");
        display.displayInfo("In order to do that, you have to use the spell Expecto Patronum to summon your Patronus.");
        teachExpectopatronum();
        wishGoodLuck();
    }

    public void teachExpectopatronum() {
        display.displayInfo("");
        display.announceReward("You have learned the spell Expecto Patronum");
        player.learnSpell(new Expectopatronum(game, player));
        display.displayInfo("It is a very difficult spell to cast,, so it may take some time to use it successfully.");
    }

    public void setExpectoPatronumUsed() {
        this.expectoPatronumUsed = true;
    }

    public void teachLumos() {
        display.displayInfo("Now let me teach you a spell that you'll probably need soon.");
        inputParser.waitForYes("Hold your wand tightly in your hand.\n" +
                "Focus on the tip of your wand and visualize a bright light appearing there.\n" +
                "Say Lumos in a clear and firm voice while pointing your wand upward.\n" +
                "A bright light should appear at the tip of your wand, illuminating the area around you.\n" +
                "To turn off the light, simply say Nox while pointing your wand downward.");
        player.learnSpell(new Lumos(game, player));
    }
}

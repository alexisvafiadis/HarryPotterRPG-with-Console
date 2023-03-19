package Levels;

import Characters.Wizard;
import Game.Game;

public class Level4 extends Level{
    Wizard player;

    public Level4(Game game) {
        super(game, "Little Hangleton graveyard", 4, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have gotten away!");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("You have to get close to Portolion, attract him to you then get away.");
        display.displayInfo("In order to do that, you have to use the spell Accio.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            display.announceReward("You have been given the spell Accio.");
            //player.addSpell();
        }
        wishGoodLuck();
    }

}

package Levels;

import Characters.Enemies.DoloresUmbridge;
import Game.Game;
import Levels.Essentials.DoloresUmbridgeBattle;

public class Level5 extends Level{

    public Level5(Game game) {
        super(game, "The Order of the Phoenix","Great Hall", 5, false);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        DoloresUmbridge doloresUmbridge = new DoloresUmbridge(game);
        doloresUmbridge.spawn();
        new DoloresUmbridgeBattle(game, this, player, doloresUmbridge);
        if (!doloresUmbridge.isDistracted()) {
            display.announceFail("You weren't able to distract Dolores Umbridge for enough time.");
            fail();
        }
        else if (!doloresUmbridge.isAlive()) {
            display.announceFail("You have hurt Dolores Umbridge with a spell and get expelled from Hogwarts.");
        }
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have distracted Dolores Umbridge for enough time, and the fireworks are now ready");
        display.displayInfo("Let the party begin!");
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("You have to distract Dolores Umbridge until ");
        wishGoodLuck();
    }

}

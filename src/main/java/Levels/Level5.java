package Levels;

import Characters.Basilisk;
import Characters.DoloresUmbridge;
import Characters.Wizard;
import Game.Game;

public class Level5 extends Level{

    public Level5(Game game) {
        super(game, "The Order of the Phoenix","Great Hall", 5, false);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn(0,0,0);
        super.start();
        DoloresUmbridge doloresUmbridge = new DoloresUmbridge(game);
        doloresUmbridge.spawn(1,1,1);
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
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("You have to distract Dolores Umbridge until ");
        wishGoodLuck();
    }

}

package Levels;

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
        introduce();

    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have defeated Dolores Umbridge");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("You have to distract Dolores Umbridge until ");
        wishGoodLuck();
    }

}

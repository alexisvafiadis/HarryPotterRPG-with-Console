package Levels;

import Characters.Wizard;
import Game.Game;

public class Level5 extends Level{
    Wizard player;
    Game game;

    public Level5(Game game) {
        super(game, "Great Hall", 5, false);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        introduce();

    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have defeated Dolores Ombrage!");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("You have to fight ");
        display.displayInfo("In order to do that, you have to use the spell Accio.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            display.announceReward("You have been given the spell Accio.");
            //player.addSpell();
        }
        wishGoodLuck();
    }

    @Override
    public String getPlace() {
        return place;
    }

    @Override
    public int getNumber() {
        return number;
    }
}

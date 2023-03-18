package Levels;

import Characters.Wizard;
import Game.Game;

public class Level5 extends Level{
    Wizard player;
    final String place = "Chamber of Secrets";
    Game game;

    public Level5(Game game) {
        super(game);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

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
        display.displayInfo("You have to fight ");
        display.displayInfo("In order to do that, you have to use the spell Accio.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            display.announceReward("You have been given the spell Accio.");
            //player.addSpell();
        }
        display.displayInfo("Good luck!");
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

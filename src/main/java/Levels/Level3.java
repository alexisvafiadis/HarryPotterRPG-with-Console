package Levels;

import Characters.Troll;
import Characters.Wizard;
import Game.Game;

public class Level3 extends Level{
    Wizard player;
    Troll troll;
    final String place = "Chamber of Secrets";
    Game game;

    public Level3(Game game) {
        super(game);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    public void start() {
        introduce();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed scared the Dementors away!");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        display.displayInfo("For this level, you have to scare the Dementors away.");
        display.displayInfo("In order to do that, you have to use the spell Expecto Patronum to summon your Patronus");
        display.announceReward("You have learned the spell Expecto Patronum");
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

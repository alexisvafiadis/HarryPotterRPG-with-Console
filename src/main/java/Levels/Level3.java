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
    }

    public void start() {
        introduce(player);
    }

    @Override
    public void conclude(Wizard wizard) {
        game.congratulate("Well done, you have killed scared the Dementors away!");
        askForUpgrade();
    }

    @Override
    public void introduce(Wizard wizard) {
        game.displayInfo("For this level, you have to scare the Dementors away.");
        game.displayInfo("In order to do that, you have to use the spell Expecto Patronum to summon your Patronus");
        game.announceReward("You have learned the spell Expecto Patronum");
        game.displayInfo("Good luck!");
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

package Levels;

import Characters.Troll;
import Characters.Wizard;
import Game.Game;

public class Level2 extends Level{
    Wizard player;
    Troll troll;
    final String place = "Chamber of Secrets";
    Game game;

    public Level2(Game game) {
        super(game);
    }

    public void start() {
        introduce(player);
        Troll troll = new Troll(game, 0, 0, 0);
        game.getSc().nextInt();
    }

    @Override
    public void conclude(Wizard wizard) {
        game.congratulate("Well done, you have killed the Basilic");
        askForUpgrade();
    }

    @Override
    public void introduce(Wizard wizard) {
        System.out.println("For this level, you have to defeat the Basilic.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            game.displayInfo("In order to do that, you have to use the Gryffindor sword. Here it is.");
            game.announceReward("You have been given the Gryffindor sword");
        }
        else {
            game.displayInfo("In order to do that, you have to use the spell Accio to ");
            game.announceReward("You have learned the Accio spell");
        }
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

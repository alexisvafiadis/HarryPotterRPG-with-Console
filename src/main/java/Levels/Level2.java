package Levels;

import Characters.Troll;
import Characters.Wizard;
import Extra.Item;
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
        if (player.getHouse().toString().equals("Gryffindor")) {
            System.out.println("In order to do that, you have to use the Gryffindor sword. Here it is.");
            game.announceReward("Therefore, you have been given the Gryffindor sword");
        }
        else {
            System.out.println("In order to do that, you have to use the spell Accio to ");
            game.announceReward("Therefore, you have learned the Accio spell");
        }
        Troll troll = new Troll(game, 0, 0, 0);
        game.getSc().nextInt();
    }

    @Override
    public void conclude(Wizard wizard) {
        System.out.println("Well done, you have killed the Basilic");
        askForUpgrade();
    }

    @Override
    public void introduce(Wizard wizard) {
        System.out.println("For this level, you have to defeat the Troll.");
        System.out.println("In order to do that, you have to lift items and throw them at the Troll's head");
        System.out.println("Good luck!");
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

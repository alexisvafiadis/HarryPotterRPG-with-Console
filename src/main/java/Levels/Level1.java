package Levels;

import Characters.Troll;
import Characters.Wizard;
import Game.Game;

public class Level1 extends Level{
    Wizard player;
    Troll troll;
    final String place = "Toilets of the Dungeon";
    Game game;

    public Level1(Game game) {
        super(game);
        this.game = game;
        player = game.getPlayer();
    }

    public void start() {
        System.out.println("In this level, you have to beat the Troll.");
        Troll troll = new Troll(game);
        game.getSc().nextInt();
    }

    @Override
    public void conclude(Wizard wizard) {
        System.out.println("Well done, you have killed the Troll");
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

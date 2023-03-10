package Levels;

import Characters.Troll;
import Characters.Wizard;
import Extras.Item;
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
        game.displayInfo("In this level, you have to beat the Troll.");
        game.displayInfo("In order to do that, you have to use the Wingardium Leviosa spell to throw items on the Troll's head");
        player.spawn(2, 2, 2);
        game.announceReward("Therefore, you have learned Wingardium Leviosa!");
        Troll troll = new Troll(game, 0, 0, 0);
        Item item = new Item(1,1,0);
        game.getSc().nextInt();
    }

    @Override
    public void conclude(Wizard wizard) {
        game.congratulate("Well done, you have killed the Troll");
        askForUpgrade();
    }

    @Override
    public void introduce(Wizard wizard) {
        game.displayInfo("For this level, you have to defeat the Troll.");
        game.displayInfo("In order to do that, you have to lift items and throw them at the Troll's head");
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

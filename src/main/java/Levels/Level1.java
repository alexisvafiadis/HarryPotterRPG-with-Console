package Levels;

import Characters.Enemies.Troll;
import Characters.Wizard;
import Game.Game;
import Levels.Essentials.Battle;
import Magic.Spells.Stupefy;
import Magic.Spells.WingardiumLeviosa;

public class Level1 extends Level{
    Wizard player;

    public Level1(Game game) {
        super(game, "The Philosopher’s Stone", "Toilets of the Dungeon", 1, false);
        player = game.getPlayer();
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        Troll troll = new Troll(game);
        troll.spawn();
        new Battle(game, this, player, troll);
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed the Troll!");
        display.announceReward("You have earned the right to learn an offensive spell that may come in handy in the future");
        Stupefy stupefy = new Stupefy(game, player);
        stupefy.teach(player);
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("In this level, you have to defeat the Troll.");
        display.displayInfo("In order to do that, you have to use the Wingardium Leviosa spell to lift and throw items on the Troll's head");
        WingardiumLeviosa wingardium = new WingardiumLeviosa(game, player);
        wingardium.teach(player);
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

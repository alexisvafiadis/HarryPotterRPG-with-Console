package Levels;

import Characters.Dementor;
import Characters.Troll;
import Characters.Wizard;
import Game.Game;
import Spells.Expectopatronum;

public class Level3 extends Level{
    boolean expectoPatronumUsed;

    public Level3(Game game) {
        super(game, "The Prisoner of Azkaban","the Great Lake", 3, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn(0,0,0);
        super.start();
        expectoPatronumUsed = false;
        while (!expectoPatronumUsed) {
            new Battle(game, this, player, new Dementor(game));
        }
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed scared the Dementors away!");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("For this level, you have to scare the Dementors away.");
        display.displayInfo("In order to do that, you have to use the spell Expecto Patronum to summon your Patronus.");
        teachExpectopatronum();
        wishGoodLuck();
    }

    public void teachExpectopatronum() {
        display.displayInfo("");
        display.announceReward("You have learned the spell Expecto Patronum");
        player.learnSpell(new Expectopatronum(game, player));
        display.displayInfo("It is a very difficult spell to cast,, so it may take some time to use it successfully.");
    }

    public void setExpectoPatronumUsed() {
        this.expectoPatronumUsed = true;
    }
}

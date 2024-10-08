package Core.Levels;

import Core.Characters.Enemies.DoloresUmbridge;
import Core.Game.Game;
import Core.Levels.Essentials.DoloresUmbridgeBattle;
import Core.Magic.Spells.Reducto;

public class Level5 extends Level{
    private DoloresUmbridge doloresUmbridge;

    public Level5(Game game) {
        super(game, "The Order of the Phoenix","Great Hall", 5, false);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        doloresUmbridge = new DoloresUmbridge(game);
        doloresUmbridge.spawn();
        new DoloresUmbridgeBattle(game, this, player, doloresUmbridge).start();
        if (!doloresUmbridge.isDistracted()) {
            display.announceFail("You weren't able to distract Dolores Umbridge for enough time.");
            fail();
        }
        else if (!doloresUmbridge.isAlive()) {
            display.announceFail("You have hurt Dolores Umbridge with a spell and get expelled from Hogwarts.");
            fail();
        }
        else {
            display.announceSuccess("Well done, you have distracted Dolores Umbridge for enough time!");
        }
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Thanks to you, the fireworks are now ready");
        display.displayInfo("Let the party begin!");
        display.displayInfo("20 minutes later...");
        display.displayQuote(doloresUmbridge, "Ok, enough now! It's actually the perfect situation to teach you all a new spell.");
        display.displayInfo("Dolores Umbridge casts a spell to destroy the firework machine.. The party is over");
        display.displayQuote(doloresUmbridge,"This spell is called Reducto and it is very useful, especially in combat.");
        (new Reducto(game, player)).teach(player);
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("In this level, you have to distract Dolores Umbridge until your fellow classmates finish preparing the fireworks.");
        display.displayInfo("She may get mad at you, but don't worry, she will have bigger fish to fry if the party really happens");
        wishGoodLuck();
    }



}

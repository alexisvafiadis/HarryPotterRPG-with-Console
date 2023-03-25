package Levels;

import Characters.Enemies.Troll;
import Characters.Wizard;
import Game.Game;
import Levels.Essentials.Battle;
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

    public void teachWingardiumLeviosa() {
        if (!player.knowsSpell("Wingardium Leviosa")) {
            display.displayInfo("To cast the Wingardium Leviosa spell, hold your wand out in front of you and say the incantation clearly: Wingardium Leviosa.");
            display.displayInfo("Next, you need to make the wand movement for the spell. With your wand in your hand, make a swishing motion upward, as if you are lifting something up into the air.");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            display.displayInfo("Good. Now that you have the spell incantation and wand movement down, you need to find an object to levitate. Look around the room and find an object that you want to move with the spell.");
            player.learnSpell(new WingardiumLeviosa(game, player));
        }
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed the Troll!");
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("In this level, you have to defeat the Troll.");
        display.displayInfo("In order to do that, you have to use the Wingardium Leviosa spell to lift and throw items on the Troll's head");
        teachWingardiumLeviosa();
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

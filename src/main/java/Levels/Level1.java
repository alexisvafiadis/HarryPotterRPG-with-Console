package Levels;

import Characters.Troll;
import Characters.Wizard;
import Extras.Item;
import Extras.ItemType;
import Game.Game;
import Spells.Spell;
import Spells.WingardiumLeviosa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level1 extends Level{
    Wizard player;
    Troll troll;
    final String place = "Toilets of the Dungeon";
    Game game;

    public Level1(Game game) {
        super(game);
        setOutdoors(false);
        this.game = game;
        player = game.getPlayer();
        display = game.getDisplay();
        inputParser = game.getInputParser();

    }

    public void start() {
        display.displayInfo("In this level, you have to beat the Troll.");
        display.displayInfo("In order to do that, you have to use the Wingardium Leviosa spell to throw items on the Troll's head");
        if (!player.knowsSpell("Wingardium Leviosa")) {
            teachWingardiumLeviosa();
            player.learnSpell(new WingardiumLeviosa(game, player));
        }
        player.spawn(2, 2, 2);
        Troll troll = new Troll(game);
        troll.spawn(0, 0, 0);
        fight(troll);
        finish();
    }

    public void teachWingardiumLeviosa() {
        display.displayInfo("To cast the Wingardium Leviosa spell, hold your wand out in front of you and say the incantation clearly: \"\\033[3mWingardium Leviosa\\033[0m\".");
        //check the user says wing
        display.displayInfo("Next, you need to make the wand movement for the spell. With your wand in your hand, make a swishing motion upward, as if you are lifting something up into the air.");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        display.displayInfo("Good. Now that you have the spell incantation and wand movement down, you need to find an object to levitate. Look around the room and find an object that you want to move with the spell.");
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed the Troll!");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        display.displayInfo("For this level, you have to defeat the Troll.");
        display.displayInfo("In order to do that, you have to lift items and throw them at the Troll's head");
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

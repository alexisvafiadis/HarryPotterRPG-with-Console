package Levels;

import Characters.Troll;
import Characters.Wizard;
import Extras.Item;
import Extras.ItemType;
import Game.Game;
import Spells.Spell;
import Spells.WingardiumLeviosa;
import Tools.ProjectTools;

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
    }

    public void start() {
        game.displayInfo("In this level, you have to beat the Troll.");
        game.displayInfo("In order to do that, you have to use the Wingardium Leviosa spell to throw items on the Troll's head");
        player.spawn(2, 2, 2);
        player.learnSpell(new WingardiumLeviosa(game, player));
        mainAction();
    }

    public void mainAction() {
        Troll troll = new Troll(game, 0, 0, 0);
    }

    public void learnWingardiumLeviosa(int step) {
        if (step == 0) {
            game.displayInfo("To cast the Wingardium Leviosa spell, hold your wand out in front of you and say the incantation clearly: \"\\033[3mWingardium Leviosa\\033[0m\".");
            //check the user says wing
            game.displayInfo("Next, you need to make the wand movement for the spell. With your wand in your hand, make a swishing motion upward, as if you are lifting something up into the air.");
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
            }
            game.displayInfo("Good. Now that you have the spell incantation and wand movement down, you need to find an object to levitate. Look around the room and find an object that you want to move with the spell.");
        }
        else {
            game.displayInfo("Aim your wand at the object you want to levitate and concentrate on it. Visualize the object lifting up off the ground and floating in the air.\n" +
                    "\n" +
                    "As you concentrate on the object, say the incantation \"Wingardium Leviosa\" and make the wand movement. If you did it correctly, the object should start to lift off the ground.\n" +
                    "\n" +
                    "Once the object is floating in the air, you can move it around by pointing your wand in different directions. Try moving the object to the left, right, up, and down by changing the direction of your wand.\n" +
                    "\n" +
                    "To release the object, simply stop concentrating on it and let it fall back to the ground.");
        }
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

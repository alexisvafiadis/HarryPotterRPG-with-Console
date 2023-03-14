package Levels;

import Characters.Troll;
import Characters.Wizard;
import Extras.Item;
import Extras.ItemType;
import Game.Game;
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
        game.announceReward("Therefore, you have learned Wingardium Leviosa!");
        mainAction();
    }

    public void mainAction() {
        Troll troll = new Troll(game, 0, 0, 0);
        int i = 1;
        while (troll.isAlive()) {
            HashMap<Integer, String> validInputs = new HashMap<>();
            validInputs.put(1, "Look around");
            validInputs.put(2, "Attack");
            validInputs.put(3, "Hide");
            if (player.hasAnyPotion()) {
                validInputs.put(4, "Use a potion");
            }
            String choice = ProjectTools.getNumberToStringInput(game.getSc(), "What do you want to do?", validInputs, "to");
            switch (choice) {
                case "Look around":
                    double random = Math.random();
                    if (random < 0.3) {
                        game.announceDiscovery("Good job, you have found a potion!");
                        double potionRandom = Math.random();
                    }
                    else if (random < 0.6) {
                        ItemType randomItemType = generateItemType();
                        game.announceDiscovery("You have found an item. It looks like a " + randomItemType.toString());
                        addItem(new Item(randomItemType, 0, 0, 0));
                    }
                    else if (random < 0.9) {
                        game.displayInfo("");
                    }
                    else {
                        game.announceFail("Unfortunately, you haven't found anything.");
                    }
                    break;
                case "Attack":
                    break;
                case "Hide":
                    break;
                case "Use a potion":

            }
            if (i % 2 == 0) {
                troll.act();
            }
            i += 1;
        }
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

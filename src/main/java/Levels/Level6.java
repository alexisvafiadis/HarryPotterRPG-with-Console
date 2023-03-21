package Levels;

import Characteristics.House;
import Characters.Basilisk;
import Characters.DeathEater;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Items.Weapon;
import Game.Game;
import Spells.Accio;
import Spells.WingardiumLeviosa;

public class Level6 extends Level{

    public Level6(Game game) {
        super(game, "The Half-blood Prince","Astronomy Tower", 6, true);
    }

    @Override
    public void start() {
        player.spawn(0,0,0);
        super.start();
        if (player.getHouse().equals(House.SLYTHERIN)) {

        }
        else {
            DeathEater deathEater = new DeathEater(game);
            deathEater.spawn(1, 1, 1);
            new Battle(game, this, player, deathEater);
            finish();
        }
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed the Basilisk");
        if (!player.knowsSpell("Accio")) {
            display.displayInfo("Oh, I almost forgot. There's a spell you will need soon. It is called Accio");
            teachAccio();
        }
        askForUpgrade();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("For this level, you have to defeat the Basilisk. It is a very venomous snake that kills anyone that makes eye contact with it.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            display.displayInfo("In order to do that, you have to use the Gryffindor sword. Here it is.");
            display.announceReward("You have been given the Gryffindor sword");
            player.setWeapon(Weapon.SWORD_OF_GRYFFINDOR);
        }
        else {
            display.displayInfo("In order to do that, you will probably need the Accio spell to summon a Basilisk's fang and use it against itself.");
            teachAccio();
        }
        wishGoodLuck();
    }

    public void teachAccio() {
        if (!player.knowsSpell("Accio")) {
            inputParser.waitForYes("First, have your wand at the ready. Then, focus your attention on the object you want to summon. Visualize the object in your mind and concentrate on it.\n" +
                    "Now, hold out your wand and say \"Accio\" followed by the name of the object you want to summon. For example, \"Accio broomstick!\"\n" +
                    "If the spell is successful, the object should fly towards you. Be ready to catch it, or move out of the way if it's a larger object.\n" +
                    "Practice your casting and timing to improve your success rate with the spell. Remember, the Accio spell only works on objects within your line of sight.\n" +
                    "Understood?");
            display.announceReward("You have learned the Accio spell");
            player.learnSpell(new Accio(game, player));
        }
    }
}

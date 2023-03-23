package Levels;

import Characters.Enemies.Basilisk;
import Items.Weapon;
import Game.Game;
import Levels.Essentials.Battle;
import Spells.Accio;

public class Level2 extends Level{

    public Level2(Game game) {
        super(game, "The Chamber of Secrets","the Chamber of Secrets", 2, false);
    }

    @Override
    public void start() {
        player.spawn(0,0);
        super.start();
        Basilisk basilisk = new Basilisk(game);
        basilisk.spawn(1,1);
        new Battle(game, this, player, basilisk);
        finish();
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

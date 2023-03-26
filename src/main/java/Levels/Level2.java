package Levels;

import Characters.Enemies.Basilisk;
import Items.Weapon;
import Game.Game;
import Levels.Essentials.Battle;
import Magic.Spells.Accio;
import Magic.Spells.PetrificusTotalus;

public class Level2 extends Level{

    public Level2(Game game) {
        super(game, "The Chamber of Secrets","the Chamber of Secrets", 2, false);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        Basilisk basilisk = new Basilisk(game);
        basilisk.spawn();
        new Battle(game, this, player, basilisk);
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed the Basilisk!");
        display.displayInfo("Considering the threats you've been facing, it would be a good idea to teach you another offensive spell : Petrificus Totalus.");
        PetrificusTotalus petrificustotalus = new PetrificusTotalus(game, player);
        petrificustotalus.teach(player);
        if (!player.knowsSpell("Accio")) {
            display.displayInfo("Oh, I almost forgot. There's a spell you will need soon. It is called Accio");
            Accio accio = new Accio(game, player);
            accio.teach(player);
        }
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
            Accio accio = new Accio(game, player);
            accio.teach(player);
        }
        wishGoodLuck();
    }

}

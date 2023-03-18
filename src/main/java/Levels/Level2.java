package Levels;

import Characters.Basilic;
import Characters.Troll;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Extras.Weapon;
import Game.Game;
import Spells.Accio;

public class Level2 extends Level{
    Game game;
    Display display;
    InputParser inputParser;
    Wizard player;
    Basilic basilic;
    final String place = "Chamber of Secrets";

    public Level2(Game game) {
        super(game);
        player = game.getPlayer();
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    public void start() {
        introduce();
        player.spawn(0,0,0);
        Basilic basilic = new Basilic(game);
        basilic.spawn(1,1,1);
        fight(basilic);
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have killed the Basilic");
        askForUpgrade();
    }

    @Override
    public void introduce() {
        System.out.println("For this level, you have to defeat the Basilic.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            display.displayInfo("In order to do that, you have to use the Gryffindor sword. Here it is.");
            display.announceReward("You have been given the Gryffindor sword");
            player.setWeapon(Weapon.SWORD_OF_GRYFFINDOR);
        }
        else {
            display.displayInfo("In order to do that, you have to use the spell Accio to ");
            display.announceReward("You have learned the Accio spell");
            player.learnSpell(new Accio(game, player));
        }
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

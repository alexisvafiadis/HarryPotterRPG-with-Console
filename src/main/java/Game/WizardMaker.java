package Game;

import Characters.Wizard;
import Extras.Pet;
import Houses.Gryffindor;
import Houses.Hufflepuff;
import Houses.Ravenclaw;
import Houses.Slytherin;
import Tools.ProjectTools;

import java.util.HashMap;
import java.util.Scanner;

public class WizardMaker {
    Game game;
    public WizardMaker(Game game) {
        this.game = game;
        askPet();
        askWand();
        teachBasicSpells();
    }
    public void askPet() {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Owl");
        validInputs.put(2, "Cat");
        validInputs.put(3, "Rat");
        validInputs.put(4, "Toad");

        String houseName = ProjectTools.getNumberToStringInput(game.getSc(), "Choose your pet.'", validInputs, "for");
        System.out.println("You chose the house " + houseName + ". Have fun!");
        switch (houseName) {
            case "Owl":
                game.getPlayer().setPet(Pet.OWL);
                break;
            case "Cat":
                game.getPlayer().setPet(Pet.CAT);
                break;
            case "Rat":
                game.getPlayer().setPet(Pet.RAT);
                break;
            case "Toad":
                game.getPlayer().setPet(Pet.TOAD);
                break;
        }
    }

    public void teachBasicSpells() {
        game.displayInfo("Now let's make sure you know how to cast some basic spells.");
        game.displayInfo("We can start with Engorgio, a pretty simple spell that seems useless but is useful if you use it cleverly");
        ProjectTools.waitForYes("First, point your wand at the object you want to enlarge and say \"Engorgio\" clearly.\n" +
                "If the spell is successful, the object should become larger and more cumbersome.\n" +
                "This spell can be useful for enlarging objects to block an opponent's path or to create a barrier.\n" +
                "Be careful not to overuse this spell, as it can also make objects too big to control or to move around easily.", game.getSc());
        game.announceReward("You have learned the spell Lumos");

        /*
        ProjectTools.waitForYes("First, hold your wand in front of you and say \"Lumos\" clearly.\n" +
                "The end of your wand should now emit a small beam of light.\n" +
                "Move your wand around to aim the light in different directions.\n" +
                "This spell can be useful for exploring dark areas and finding hidden paths.\n" +
                "Understood?", game.getSc());
        game.announceReward("You have learned the spell Lumos");
         */

        game.displayInfo("Now you should learn a spell that can be useful in combat");
        ProjectTools.waitForYes("Point your wand at your opponent and say \"Confundus\" clearly.\n" +
                "If the spell is successful, your opponent should become confused and disoriented.\n" +
                "This spell can be useful for confusing opponents and creating an opportunity to attack or escape.\n" +
                "Be careful not to use it too much, as it can cause unintended consequences or lead to a loss of points." +
                "Understood?", game.getSc());
        game.announceReward("You have learned the spell Confundus");

        game.displayInfo("Another spell that you may need in combat is Expelliarmus");
        ProjectTools.waitForYes("To cast this spell, point your wand at your opponent and say \"Expelliarmus\" clearly.\n" +
                "If the spell is successful, your opponent's weapon should be knocked out of their hand.\n" +
                "This spell can be useful for disarming opponents and leaving them vulnerable to attack."
        + "Understood?", game.getSc());
        game.announceReward("You have learned the spell Expelliarmus");

        game.displayInfo("Nice, this should be enough to get you started.");
        game.displayInfo("Remember, you may not be able to cast them successfully or perfectly the first few times you use them, but don't worry, the more you use a spell and get familiar with your wand, the easier it will get.");
    }

    public void askWand() {

    }
}

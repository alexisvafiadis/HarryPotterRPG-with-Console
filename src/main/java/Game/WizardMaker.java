package Game;

import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Characteristics.Pet;
import Spells.Engorgio;
import Spells.Expelliarmus;

import java.util.HashMap;

public class WizardMaker {
    Game game;
    Display display;
    InputParser inputParser;
    Wizard player;

    public WizardMaker(Game game) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        player = game.getPlayer();
        askName();
        display.displayInfo("Welcome to Hogwarts, " + player.getName());
        display.displayInfo("Now let's get some things sorted.");
        askPet();
        askWand();
        teachBasicSpells();
    }

    public void askName() {
        display.displayRequest("Hello, what would you like your wizard name to be?");
        player.setName(inputParser.getSc().nextLine());
    }

    public void askPet() {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Owl");
        validInputs.put(2, "Cat");
        validInputs.put(3, "Rat");
        validInputs.put(4, "Toad");

        String petName = inputParser.getNumberToStringInput("Choose your pet.", validInputs, "for");
        display.displayInfo("You chose the pet " + petName + ". Have fun!");
        game.getPlayer().setPet(Pet.valueOf(petName.toUpperCase()));
    }

    public void askWand() {

    }

    public void teachBasicSpells() {
        display.displayInfo("Now let's make sure you know how to cast some basic spells.");
        display.displayInfo("We can start with Engorgio, a pretty simple spell that seems useless but can be amazing if you use it cleverly");
        inputParser.waitForYes("First, point your wand at the object you want to enlarge and say \"Engorgio\" clearly.\n" +
                "If the spell is successful, the object should become larger and more cumbersome.\n" +
                "This spell can be useful for enlarging objects to block an opponent's path or to create a barrier.\n" +
                "Be careful not to overuse this spell, as it can also make objects too big to control or to move around easily.\n" +
                "Understood?");
        display.announceReward("You have learned the spell Engorgio!");
        player.learnSpell(new Engorgio(game, player));

        /*
        ProjectTools.waitForYes("First, hold your wand in front of you and say \"Lumos\" clearly.\n" +
                "The end of your wand should now emit a small beam of light.\n" +
                "Move your wand around to aim the light in different directions.\n" +
                "This spell can be useful for exploring dark areas and finding hidden paths.\n" +
                "Understood?", game.getSc());
        game.announceReward("You have learned the spell Lumos");
         */

        display.displayInfo("Now you should learn a spell that is useful in combat");
        inputParser.waitForYes("Point your wand at your opponent and say \"Confundus\" clearly.\n" +
                "If the spell is successful, your opponent should become confused and disoriented.\n" +
                "This spell can be useful for confusing opponents and creating an opportunity to attack or escape.\n" +
                "Be careful not to use it too much, as it can cause unintended consequences or lead to a loss of points." +
                "Understood?");
        display.announceReward("You have learned the spell Confundus!");

        display.displayInfo("Another spell that you may need in combat is Expelliarmus");
        inputParser.waitForYes("To cast this spell, point your wand at your opponent and say \"Expelliarmus\" clearly.\n" +
                "If the spell is successful, your opponent's weapon should be knocked out of their hand.\n" +
                "This spell can be useful for disarming opponents and leaving them vulnerable to attack.\n"
                + "Understood?");
        display.announceReward("You have learned the spell Expelliarmus!");
        player.learnSpell(new Expelliarmus(game, player));

        display.displayInfo("Nice, this should be enough to get you started.");
        display.displayInfo("Remember, you may not be able to cast them successfully or perfectly the first few times you use them, but don't worry, the more you use a spell and get familiar with your wand, the easier it will get.");
    }
}

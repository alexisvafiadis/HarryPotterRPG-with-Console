package Game;

import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Characters.Characteristics.Pet;
import Magic.Spells.Confundus;
import Magic.Spells.Engorgio;
import Magic.Spells.Expelliarmus;
import Magic.Spells.WingardiumLeviosa;

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
        Engorgio engorgio = new Engorgio(game, player);
        engorgio.teach(player);
        display.displayInfo("Now you should learn a spell that is useful in combat");
        Confundus confundus = new Confundus(game, player);
        confundus.teach(player);
        display.displayInfo("Another spell that you may need in combat is Expelliarmus");
        Expelliarmus expelliarmus = new Expelliarmus(game, player);
        expelliarmus.teach(player);
        player.learnSpell(new Engorgio(game, player));
        display.displayInfo("Nice, this should be enough to get you started.");
        display.displayInfo("Remember, you may not be able to cast them successfully or perfectly the first few times you use them, but don't worry, the more you use a spell and get familiar with your wand, the easier it will get.");
    }
}

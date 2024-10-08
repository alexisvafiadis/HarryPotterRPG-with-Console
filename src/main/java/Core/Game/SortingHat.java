package Core.Game;

import java.util.HashMap;

import Core.Characters.Characteristics.House;
import Core.Characters.Wizard;

public class SortingHat{
    public void askHouse(Game game, Wizard wizard) {
        //Note : Made it at the very start, so that' why it's great code quality
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Gryffindor");
        validInputs.put(2, "Hufflepuff");
        validInputs.put(3, "Ravenclaw");
        validInputs.put(4, "Slytherin");

        String houseName = game.getInputParser().getNumberToStringInput("It's time to choose your house.", validInputs, "for");
        game.getDisplay().displayInfo("You chose the house " + houseName + ". Have fun!");
        wizard.setHouse(House.valueOf(houseName.toUpperCase()));
    }
}

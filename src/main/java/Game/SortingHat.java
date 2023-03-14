package Game;

import java.util.HashMap;
import java.util.Scanner;

import Characters.Wizard;
import Houses.Gryffindor;
import Houses.Hufflepuff;
import Houses.Ravenclaw;
import Houses.Slytherin;
import Tools.ProjectTools;

public class SortingHat{
    public void askHouse(Scanner sc, Wizard wizard) {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Gryffindor");
        validInputs.put(2, "Hufflepuff");
        validInputs.put(3, "Ravenclaw");
        validInputs.put(4, "Slytherin");

        String houseName = ProjectTools.getNumberToStringInput(sc, "It's time to choose your house.'", validInputs, "for");
        System.out.println("You chose the house " + houseName + ". Have fun!");
        switch (houseName) {
            case "Gryffindor":
                wizard.setHouse(new Gryffindor());
                break;
            case "Hufflepuff":
                wizard.setHouse(new Hufflepuff());
                break;
            case "Ravenclaw":
                wizard.setHouse(new Ravenclaw());
                break;
            case "Slytherin":
                wizard.setHouse(new Slytherin());
                break;
        }
    }
}

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

    public void askWand() {

    }
}

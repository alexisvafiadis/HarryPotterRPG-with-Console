package Console;

import Characters.Character;
import Game.Game;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Display {
    Game game;
    int DEFAULT_WRITING_DELAY = 15;
    final int FAST_WRITING_DELAY = 5;
    final int SLOW_WRITING_DELAY = 30;

    public Display(Game game) {
        this.game = game;
        if (game.isInDebugMode()) { DEFAULT_WRITING_DELAY = 1; }
    }

    public void slowPrint(String output, String color, boolean nextLine) {
        System.out.print(color);
        for (int i = 0; i<output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(DEFAULT_WRITING_DELAY);
            }
            catch (Exception e) {

            }
        }
        if (nextLine) {
            System.out.println();
        }
    }

    public void slowPrint(String output, String color) {slowPrint(output, color, true); }

    public void announceReward(String announcement) {
        slowPrint(announcement, ConsoleColors.YELLOW);
    }

    public void announceDiscovery(String finding) { slowPrint(finding, ConsoleColors.CYAN); }

    public void announceFail(String fail) { slowPrint(fail, ConsoleColors.RED); }

    public void announceSuccess(String success) { slowPrint(success, ConsoleColors.GREEN); }

    public void displayInfo(String information) { slowPrint(information, ConsoleColors.RESET); }

    public void displayRequest(String request) { slowPrint(request, ConsoleColors.BLUE); }

    public void congratulate(String congratulations) { slowPrint(congratulations, ConsoleColors.YELLOW_BRIGHT); }

    public void displayHP(Character character, boolean own) {
        String color, message;
        if (own) {
            color = ConsoleColors.RED_BRIGHT;
            message = "Your HP : " + Math.round(character.getHP());
        }
        else {
            color = ConsoleColors.GREEN_BRIGHT;
            message = character.getName() + "'s HP : " + Math.round(character.getHP());
        }
        slowPrint(message, color);
    }

    public void askForNumberToStringInput(String request, HashMap<Integer, String> validInputs, String linkword) {
        displayRequest(request);
        displayInfo("Please enter : ");
        for (int key : validInputs.keySet()) {
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + key + " " + ConsoleColors.RESET + linkword + " " + ConsoleColors.WHITE_BOLD_BRIGHT + validInputs.get(key));
        }
    }

}

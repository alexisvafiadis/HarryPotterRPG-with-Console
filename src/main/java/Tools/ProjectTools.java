package Tools;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjectTools {

    public static String getNumberToStringInput(Scanner sc, String request, HashMap<Integer, String> validInputs, String linkword) {
        boolean validInput = false;
        do {
            System.out.println(request);
            askForNumberToStringInput(sc, validInputs, linkword);
            try{
                sc.next();
                int input = sc.nextInt();
                if (validInputs.containsKey(input)) {
                    return validInputs.get(input);
                }
                else {

                }
            } catch(InputMismatchException e) {

            }

        } while (!validInput);
        return "";
    }

    public static void askForNumberToStringInput(Scanner sc, HashMap<Integer, String> validInputs, String linkword) {
        System.out.println("Please enter : ");
        for (int key : validInputs.keySet()) {
            System.out.println(key + linkword + validInputs.get(key));
        }
    }
}

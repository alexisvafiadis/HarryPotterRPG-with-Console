package Game;

import Characters.Wizard;
import Levels.Level;
import Levels.Level1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Wizard player;
    int currentLevelIndex;
    Scanner sc;
    List<Level> levels;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Game() {
        sc = new Scanner(System.in);
        player = new Wizard(this);
        askForName();
        introduce(sc, player);
        levels = new ArrayList<Level>();
        levels.add(new Level1(this));
        for (Level level : levels) {
            level.start();
        }
    }

    public void introduce(Scanner sc, Wizard wizard) {
        displayInfo("Welcome to Hogwarts, " + player.getName());
        displayInfo("Now let's get some things sorted.");
        WizardMaker wizardmaker = new WizardMaker(this);
        SortingHat sortingHat = new SortingHat();
        sortingHat.askHouse(sc, wizard);
    }

    public void askForName() {
        System.out.println("Hello, what would you like your wizard name to be?");
        player.setName(sc.nextLine());
    }

    public void announceReward(String announcement) {
        System.out.println(ANSI_YELLOW + announcement);
    }

    public void announceDiscovery(String finding) { System.out.println(ANSI_CYAN + finding); }

    public void announceFail(String fail) { System.out.println(ANSI_RED + fail); }

    public void displayInfo(String information) { System.out.println(ANSI_BLUE + information); }

    public void congratulate(String congratulations) { System.out.println(ANSI_GREEN + congratulations); }
    public Wizard getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelIndex);
    }

    public Scanner getSc() {
        return sc;
    }

    public void setCurrentLevel(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex;
    }
}

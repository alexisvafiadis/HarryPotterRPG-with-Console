package Game;

import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Levels.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Wizard player;
    int currentLevelIndex;
    Scanner sc;
    List<Level> levels;
    Display display;
    InputParser inputParser;
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
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        introduce(sc, player);
        levels = new ArrayList<Level>();
        levels.add(new Level1(this));
        levels.add(new Level2(this));
        levels.add(new Level3(this));
        levels.add(new Level4(this));
        levels.add(new Level5(this));
        for (Level level : levels) {
            level.start();
        }
    }

    public void introduce(Scanner sc, Wizard wizard) {
        WizardMaker wizardmaker = new WizardMaker(this);
        SortingHat sortingHat = new SortingHat();
        sortingHat.askHouse(this, wizard);
    }

    public Wizard getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelIndex);
    }

    public void setCurrentLevel(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex;
    }

    public Display getDisplay() {
        return display;
    }

    public InputParser getInputParser() {
        return inputParser;
    }
}

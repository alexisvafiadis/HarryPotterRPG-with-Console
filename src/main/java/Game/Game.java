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
    List<Level> levels;
    Display display;
    InputParser inputParser;

    int currentLevelIndex;
    boolean DEBUG_MODE = true;

    public Game() {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        introduce(player);
        levels = new ArrayList<Level>();
        levels.add(new Level1(this));
        levels.add(new Level2(this));
        levels.add(new Level3(this));
        levels.add(new Level4(this));
        levels.add(new Level5(this));
        levels.get(1).start();
        for (Level level : levels) {
            level.start();
        }
    }

    public void introduce(Wizard wizard) {
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

    public boolean isInDebugMode() {
        return DEBUG_MODE;
    }
}

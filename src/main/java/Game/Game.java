package Game;

import Characters.Character;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Levels.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Level> levels;
    private Display display;
    private InputParser inputParser;
    private Wizard player;

    int currentLevelNumber;
    final boolean DEBUG_MODE = true;

    public Game() {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        introduce(player);
        levels = new ArrayList<>();
        levels.add(new Level1(this));
        levels.add(new Level2(this));
        levels.add(new Level3(this));
        levels.add(new Level4(this));
        levels.add(new Level5(this));
        startLevel(4);
        for (int i = 1; i < 8 ; i++) {
            startLevel(i);
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
        return levels.get(currentLevelNumber - 1);
    }

    public void startLevel(int levelNumber) {
        this.currentLevelNumber = levelNumber;
        levels.get(levelNumber - 1).start();
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

    public String getMessageStartHave(Character character) {
        String castMessageStart;
        if (character instanceof Wizard) {
            castMessageStart = "You have";
        }
        else {
            castMessageStart = character.getName() + "has";
        }
        return castMessageStart;
    }

    public String getMessageStartBe(Character character) {
        String castMessageStart;
        if (character instanceof Wizard) {
            castMessageStart = "You are";
        }
        else {
            castMessageStart = character.getName() + "is";
        }
        return castMessageStart;
    }
}

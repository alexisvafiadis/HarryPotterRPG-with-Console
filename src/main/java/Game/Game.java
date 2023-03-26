package Game;

import Characters.Character;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Levels.*;
import Magic.Spells.*;

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
        if (!isInDebugMode()) {
            player.learnSpell(new Accio(this, player));
            player.learnSpell(new WingardiumLeviosa(this, player));
            player.learnSpell(new Lumos(this, player));
            player.learnSpell(new Reducto(this, player));
            player.learnSpell(new Confundus(this, player));
            player.learnSpell(new Expelliarmus(this, player));
            player.learnSpell(new Protego(this, player));
            player.learnSpell(new Rictumsempra(this, player));
            player.learnSpell(new Sectumsempra(this, player));
            player.learnSpell(new Tarantallegra(this, player));
            player.learnSpell(new Stupefy(this, player));
            player.learnSpell(new SlugulusErecto(this, player));
        }
        for (int i = 1; i < 8 ; i++) {
            startLevel(i);
        }
        finish();
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

    public void setLevel(Level level) {

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
            castMessageStart = character.getName() + " has";
        }
        return castMessageStart;
    }

    public String getMessageStartBe(Character character) {
        String castMessageStart;
        if (character instanceof Wizard) {
            castMessageStart = "You are";
        }
        else {
            castMessageStart = character.getName() + " is";
        }
        return castMessageStart;
    }

    public void finish() {
        display.congratulate("You have finished the game, congratulations!");
    }
}

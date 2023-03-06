package Game;

import Characters.Wizard;
import Extra.SortingHat;
import Levels.Level;
import Levels.Level1;

import java.util.Scanner;

public class Game {
    Wizard player;
    Level currentLevel;
    Scanner sc;

    public Game() {
        sc = new Scanner(System.in);
        player = new Wizard(this);
        introduce(sc, player);
        currentLevel = new Level1(this);
        currentLevel.start();
    }

    public void introduce(Scanner sc, Wizard wizard) {
        System.out.println("Welcome to Hogwarts!");
        SortingHat sortingHat = new SortingHat();
        sortingHat.askHouse(sc, wizard);
    }

    public void fail() {
        System.out.println("You failed this level. Try again!");
    }

    public Wizard getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}

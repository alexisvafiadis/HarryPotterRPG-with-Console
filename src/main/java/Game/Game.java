package Game;

import Characters.Wizard;
import Extra.SortingHat;
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
        System.out.println("Welcome to Hogwarts, " + player.getName());
        SortingHat sortingHat = new SortingHat();
        sortingHat.askHouse(sc, wizard);
    }

    public void askForName() {
        System.out.println("Hello, what would you like your wizard name to be?");
        player.setName(sc.nextLine());
    }
    public Wizard getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelIndex);
    }

    public Scanner getSc() {
        return sc;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevelIndex = currentLevelIndex;
    }

    public void announceReward(String announcement) {
        System.out.println(announcement);
    }
}

package Levels;

import Characters.Wizard;
import Game.Game;
import Tools.ProjectTools;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Level {
    int number;
    String place;
    Game game;
    final double HP_UPGRADE = 10;
    final double ATTACK_DAMAGE_UPGRADE = 10;
    final double DAMAGE_RESISTANCE_UPGRADE = 10;
    final double ACCURACY_UPGRADE = 10;
    Wizard player;

    public Level(Game game) {
        this.game = game;
        player = game.getPlayer();
    }

    public abstract void introduce(Wizard wizard);

    public abstract void start();

    public abstract void conclude(Wizard wizard);

    public void fail() {
        System.out.println("You failed this level. Try again!");
    }

    public void finish(Wizard wizard) {
        System.out.println("Congratulations, you have completed this level");
        conclude(wizard);
        askForUpgrade();
    }

    public String getPlace() {
        return place;
    }

    public int getNumber() {
        return number;
    }

    public void askForUpgrade() {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Hitpoints");
        validInputs.put(2, "Attack Damage");
        validInputs.put(3, "Damage Resistance");
        validInputs.put(4, "Accuracy");
        String upgrade_choice = ProjectTools.getNumberToStringInput(game.getSc(), "What do you want to upgrade?", validInputs);
        switch (upgrade_choice) {
            case "Hitpoints":
                game.getPlayer().upgradeHP(HP_UPGRADE);
                break;
            case "Attack Damage":
                game.getPlayer().upgradeDamage(ATTACK_DAMAGE_UPGRADE);
                break;
            case "Damage Resistance":
                game.getPlayer().upgradeResistance(DAMAGE_RESISTANCE_UPGRADE);
                break;
            case "Accuracy":
                game.getPlayer().upgradeAccuracy(ACCURACY_UPGRADE);
                break;
        }
    }

}

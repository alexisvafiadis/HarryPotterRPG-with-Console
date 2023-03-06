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
    double HP_UPGRADE = 10;
    double ATTACK_DAMAGE_UPGRADE;
    double DAMAGE_RESISTANCE_UPGRADE;
    double ACCURACY_UPGRADE;

    public Level(Game game) {
        this.game = game;
    }

    public abstract void introduce(Wizard wizard);

    public abstract void start();

    public abstract void conclude(Wizard wizard);

    public void finishLevel(Wizard wizard) {
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

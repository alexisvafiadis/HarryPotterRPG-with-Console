package Levels;

import Characters.Wizard;
import Extras.Item;
import Extras.ItemType;
import Game.Game;
import Tools.ProjectTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class Level {
    int number;
    String place;
    boolean outdoors;
    Game game;
    final double HP_UPGRADE = 10;
    final double ATTACK_DAMAGE_UPGRADE = 10;
    final double DAMAGE_RESISTANCE_UPGRADE = 10;
    final double ACCURACY_UPGRADE = 10;
    Wizard player;
    List<Item> items;
    HashMap<ItemType, Integer> itemTypeWeights;

    public Level(Game game) {
        this.game = game;
        player = game.getPlayer();
        items = new ArrayList<>();
        initiateItemChances();
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

    public boolean isOutdoors() {
        return outdoors;
    }

    public void setOutdoors(boolean outdoors) {
        this.outdoors = outdoors;
    }

    public void askForUpgrade() {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Hitpoints");
        validInputs.put(2, "Attack Damage");
        validInputs.put(3, "Damage Resistance");
        validInputs.put(4, "Accuracy");
        String upgrade_choice = ProjectTools.getNumberToStringInput(game.getSc(), "What do you want to upgrade?", validInputs, "for");
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

    public void initiateItemChances() {
        if (outdoors) {
            itemTypeWeights.put(ItemType.BIG_ROCK, 3);
            itemTypeWeights.put(ItemType.SMALL_ROCK, 1);
            itemTypeWeights.put(ItemType.MEDIUM_ROCK, 2);
            itemTypeWeights.put(ItemType.SMALL_STICK, 2);
            itemTypeWeights.put(ItemType.MEDIUM_STICK, 2);
            itemTypeWeights.put(ItemType.BIG_STICK, 3);
            itemTypeWeights.put(ItemType.NETTLE, 4);
        }
        else {
            itemTypeWeights.put(ItemType.BOOK, 4);
            itemTypeWeights.put(ItemType.BOTTLE, 2);
            itemTypeWeights.put(ItemType.FORK, 1);
            itemTypeWeights.put(ItemType.KNIFE, 1);
            itemTypeWeights.put(ItemType.GLASS, 2);
            itemTypeWeights.put(ItemType.PAINTING, 3);
            itemTypeWeights.put(ItemType.VASE, 3);
            itemTypeWeights.put(ItemType.LAMP, 4);
            itemTypeWeights.put(ItemType.TABLE, 2);
            itemTypeWeights.put(ItemType.CHAIR, 3);
        }

    }

    public ItemType generateItemType() {
        // I used weighted randomness instead of generating a random number from 0 to 1 and using a lot of ifs so that if I add an item I don't need to
        // change the other numbers
        // Inspired from https://stackoverflow.com/questions/6737283/weighted-randomness-in-java
        ItemType itemType = null;
        double totalWeight = 0.0;
        for (int w : itemTypeWeights.values()) {
            totalWeight += w;
        }

        double r = Math.random() * totalWeight;
        for (ItemType it : itemTypeWeights.keySet()) {
            r -= itemTypeWeights.get(it);
            if (r <= 0.0) {
                itemType = it;
                break;
            }
        }
        return itemType;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

}

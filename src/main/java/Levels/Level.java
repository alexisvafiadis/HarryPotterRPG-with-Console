package Levels;

import Characters.AbstractEnemy;
import Characters.Boss;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Extras.Item;
import Extras.ItemType;
import Game.Game;
import Potions.Potion;
import Potions.PotionType;
import Spells.Confundus;
import Spells.Engorgio;
import Spells.Spell;
import Spells.WingardiumLeviosa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class Level {
    Game game;
    Display display;
    InputParser inputParser;
    int number;
    String place;
    boolean outdoors;
    final double HP_UPGRADE = 10;
    final double ATTACK_DAMAGE_UPGRADE = 10;
    final double DAMAGE_RESISTANCE_UPGRADE = 10;
    final double ACCURACY_UPGRADE = 10;
    Wizard player;
    List<Item> items;
    HashMap<ItemType, Integer> itemTypeWeights = new HashMap<>();

    public Level(Game game) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        this.player = game.getPlayer();
        items = new ArrayList<>();
        initiateItemChances();
    }


    public abstract void introduce();

    public abstract void start();

    public abstract void conclude();

    public void fail() { display.announceFail("You failed this level. Try again!"); }

    public void finish() {
        conclude();
        display.announceSuccess("Congratulations, you have completed this level!");
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
        String upgrade_choice = inputParser.getNumberToStringInput("What do you want to upgrade?", validInputs, "for");
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

    public void fight(AbstractEnemy enemy) {
        boolean hiding;
        HashMap<Integer, String> optionInputs = new HashMap<>();
        optionInputs.put(1, "Look around");
        optionInputs.put(2, "Cast a spell");
        optionInputs.put(3, "Hide");
        HashMap<Integer, String> spellInputs = getSpellInputs();
        int roundNumber = 1;
        while (enemy.isAlive()) {
            display.displayHP(player, true);
            display.displayHP(enemy, false);
            hiding = false;
            if (player.hasAnyPotion() && !optionInputs.containsKey(4)) {
                optionInputs.put(4, "Use a potion");
            }
            String choice = inputParser.getNumberToStringInput("What do you want to do?", optionInputs, "to");
            switch (choice) {
                case "Look around":
                    double random = Math.random();
                    if (random < 0.5) {
                        PotionType potionType =  generatePotionType();
                        display.announceDiscovery("Good job, you have found a " + potionType.toString() + "!");
                        player.addPotion(new Potion(game, player, potionType));
                    }
                    else if (random < 0.9) {
                        ItemType randomItemType = generateItemType();
                        display.announceDiscovery("You have found an item. It looks like a " + randomItemType.toString());
                        addItem(new Item(randomItemType, 0, 0, 0));
                    }
                    else {
                        display.announceFail("Unfortunately, you haven't found anything.");
                    }
                    break;
                case "Cast a spell":
                    String spellChoice = inputParser.getNumberToStringInput("What spell do you want to use?", spellInputs, "for");
                    switch(spellChoice) {
                        case "Wingardium Leviosa":
                            if (getItems().isEmpty()) {
                                display.announceFail("You haven't found any item to levitate. Try looking around.");
                            } else {
                                HashMap<Integer, String> itemInputs = getItemInputs();
                                int itemIndex = inputParser.getNumberInput("Choose an item to levitate", itemInputs, "for");
                                Item item = items.get(itemIndex);
                                ((WingardiumLeviosa) player.getKnownSpells().get(spellChoice)).cast(item, enemy);
                            }
                            break;
                        case "Expelliarmus":
                            if (enemy.getWeapon() == null) {
                                display.announceFail("The " + enemy.getName() + " doesn't have a weapon");
                            } else if (enemy.isDisarmed()) {
                                display.announceFail("You have already disarmed the " + enemy.getName() + ", so this was uneffective");
                            } else {
                                enemy.setDisarmed(true);
                                enemy.attackedByExpelliarmus();
                                display.announceSuccess("You disarmed the " + enemy.getName());
                            }
                            break;
                        case "Engorgio":
                            if (getItems().isEmpty()) {
                                display.announceFail("You haven't found any item to engorge. Try looking around.");
                            } else {
                                HashMap<Integer, String> itemInputs = getItemInputs();
                                int itemIndex = inputParser.getNumberInput("Choose an item to engorge", itemInputs, "for");
                                Item item = items.get(itemIndex);
                                ((Engorgio) player.getKnownSpells().get(spellChoice)).cast(item);
                            }
                            break;
                        case "Confundus":
                            ((Confundus) player.getKnownSpells().get(spellChoice)).cast(enemy);
                    }
                    break;
                case "Hide":
                    hiding = true;
                    display.displayInfo("You are now hiding");
                    break;
                case "Use a potion":
                    player.chooseAndConsumePotion();
                    break;
            }
            enemy.finishRound();
            player.finishRound();
            if (roundNumber % enemy.getAttackDelay() == 0) {
                if (!hiding || Math.random() > 0.7) {
                    enemy.act();
                }
                else {
                    display.announceSuccess("Well done, " + enemy.getName() + " couldn't find you!");
                }
            }
            roundNumber += 1;
        }
    }

    public HashMap<Integer, String> getSpellInputs() {
        HashMap<Integer, String> spellInputs = new HashMap<>();
        int i = 0;
        HashMap<String, Spell> playerKnownSpells = player.getKnownSpells();
        for (String spellName : playerKnownSpells.keySet()) {
            spellInputs.put(i, spellName);
            i++;
        }
        return spellInputs;
    }

    public HashMap<Integer, String> getItemInputs() {
        HashMap<Integer, String> itemInputs = new HashMap<>();
        for (int itemIndex = 0; itemIndex < items.size() - 1 ; itemIndex++) {
            itemInputs.put(itemIndex, items.get(itemIndex).getItemType().toString());
        }
        return itemInputs;
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

    public PotionType generatePotionType() {
        PotionType[] potionTypes = PotionType.values();

        double totalWeight = 0.0;
        for (PotionType pt : potionTypes) {
            totalWeight += pt.getWeight();
        }

        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < potionTypes.length - 1; ++idx) {
            r -= potionTypes[idx].getWeight();
            if (r <= 0.0) break;
        }
        return potionTypes[idx];
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

}

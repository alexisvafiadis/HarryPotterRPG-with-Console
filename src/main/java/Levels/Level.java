package Levels;

import Characters.AbstractEnemy;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Items.Item;
import Items.ItemType;
import Game.Game;
import Items.Weapon;
import Potions.Potion;
import Potions.PotionType;
import Spells.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class Level {
    //Global attributes
    protected Game game;
    protected Display display;
    protected InputParser inputParser;
    protected Wizard player;

    //Level specific attributes
    protected int number;
    protected String place;
    protected boolean outdoors;
    protected final double HP_UPGRADE = 10;
    protected final double ATTACK_DAMAGE_UPGRADE = 10;
    protected final double DAMAGE_RESISTANCE_UPGRADE = 10;
    protected final double ACCURACY_UPGRADE = 10;
    protected List<Item> items;
    protected List<ItemType> possibleItemTypes;

    public Level(Game game, String place, int number, boolean outdoors) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        this.player = game.getPlayer();

        this.place = place;
        this.number = number;
        this.outdoors = outdoors;
    }

    public abstract void introduce();

    public void start() {
        items = new ArrayList<>();
        setPossibleItemTypes();
        introduce();
    }

    public abstract void conclude();

    public void fail() {
        display.announceFail("You failed this level. Try again!");
        start();
    }

    public void finish() {
        conclude();
        display.announceSuccess("Congratulations, you have completed the level " + number + "!");
        askForUpgrade();
    }

    public void wishGoodLuck() {display.displayInfo("Good luck!"); }

    public void giveLevelInfo() {display.displayInfo("--- Level " + number + " : " + "the " + place + " ---");}

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
        boolean hiding = false;
        HashMap<Integer, String> spellInputs = getSpellInputs();
        int roundNumber = 1;
        while (enemy.isAlive()) {
            display.displayHP(player, true);
            display.displayHP(enemy, false);
            if (hiding) {
                hiding = false;
                display.displayInfo("You are no longer hiding.");
            }
            HashMap<Integer, String> optionInputs = new HashMap<>();
            optionInputs.put(1, "Look around");
            optionInputs.put(2, "Cast a spell");
            optionInputs.put(3, "Hide");
            if (player.hasAnyPotion()) {
                optionInputs.put(4, "Use a potion");
            }
            if (player.hasWeapon()) {
                int choiceNumber = 4;
                if (player.hasAnyPotion())  { choiceNumber = 5;}
                optionInputs.put(choiceNumber, "Attack with your weapon");
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
                                //gotta be careful here, continue could cause problems in the round system
                                continue;
                            } else {
                                display.displayInfo(items.toString());
                                HashMap<Integer, String> itemInputs = getItemInputs();
                                int itemIndex = inputParser.getNumberInput("Choose an item to levitate", itemInputs, "for");
                                Item item = items.get(itemIndex);
                                if (((WingardiumLeviosa) player.getKnownSpells().get(spellChoice)).cast(item, enemy)) {
                                    items.remove(item);
                                }
                            }
                            break;
                        case "Expelliarmus":
                            if (enemy.isDisarmed()) {
                                display.announceFail("You have already disarmed the " + enemy.getName() + ", so this was uneffective");
                            } else if (!enemy.hasWeapon()) {
                                display.announceFail(enemy.getName() + " doesn't have a weapon");
                            } else {
                                enemy.setDisarmed(true);
                                enemy.attackedByExpelliarmus();
                                display.announceSuccess("You disarmed the " + enemy.getName());
                            }
                            break;
                        case "Engorgio":
                            if (getItems().isEmpty()) {
                                display.announceFail("You haven't found any item to engorge. Try looking around.");
                                //gotta be careful here, continue could cause problems in the round system
                                continue;
                            } else {
                                HashMap<Integer, String> itemInputs = getItemInputs();
                                int itemIndex = inputParser.getNumberInput("Choose an item to engorge", itemInputs, "for");
                                Item item = items.get(itemIndex);
                                ((Engorgio) player.getKnownSpells().get(spellChoice)).cast(item);
                            }
                            break;
                        case "Confundus":
                            ((Confundus) player.getKnownSpells().get(spellChoice)).cast(enemy);
                            break;
                        case "Accio":
                            ((Accio) player.getKnownSpells().get(spellChoice)).cast(Weapon.BASILISK_FANG);
                            break;
                    }
                    break;
                case "Hide":
                    hiding = true;
                    display.displayInfo("You are now hiding");
                    break;
                case "Use a potion":
                    player.chooseAndConsumePotion();
                    break;
                case "Attack with your weapon":
                    player.attack(enemy);
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
        for (int itemIndex = 0; itemIndex < items.size() ; itemIndex++) {
            itemInputs.put(itemIndex, items.get(itemIndex).getItemType().toString());
        }
        return itemInputs;
    }

    public void setPossibleItemTypes() {
        possibleItemTypes = new ArrayList<>(Arrays.asList(ItemType.values()));
        if (outdoors) { possibleItemTypes.removeIf(it -> (it.getWhere() == 0)); }
        else { possibleItemTypes.removeIf(it -> (it.getWhere() == 1)); }
    }

    public ItemType generateItemType() {
        // I used weighted randomness instead of generating a random number from 0 to 1 and using a lot of ifs so that if I add an item I don't need to
        // change the other numbers
        // Inspired from https://stackoverflow.com/questions/6737283/weighted-randomness-in-java

        double totalWeight = 0.0;
        for (ItemType pt : possibleItemTypes) {
            totalWeight += pt.getWeight();
        }

        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < possibleItemTypes.size() - 1; ++idx) {
            r -= possibleItemTypes.get(idx).getWeight();
            if (r <= 0.0) break;
        }
        return possibleItemTypes.get(idx);
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNumber() {
        return number;
    }

    public boolean isOutdoors() {
        return outdoors;
    }

}

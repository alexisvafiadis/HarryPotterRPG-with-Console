package Levels;

import Characters.AbstractEnemy;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Game.Game;
import Items.Item;
import Items.ItemType;
import Items.Weapon;
import Potions.ActiveEffect;
import Potions.EffectType;
import Potions.Potion;
import Potions.PotionType;
import Spells.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Battle {
    protected Game game;
    protected Display display;
    protected InputParser inputParser;

    protected Level level;
    protected Wizard player;
    protected AbstractEnemy enemy;
    protected int roundNumber;
    protected HashMap<Integer, String> spellInputs;

    public Battle(Game game, Level level, Wizard player, AbstractEnemy enemy) {
        this.game = game;
        this.display = game.getDisplay();
        this.inputParser = game.getInputParser();
        this.level = level;
        this.player = player;
        this.enemy = enemy;
        roundNumber = 1;
        spellInputs = getSpellInputs();

        while (getFightEndCondition()) {
            display.displayHP(player, true);
            display.displayHP(enemy, false);
            askPlayerForAction();
            enemyAttack();
            nextRound();
        }
    }

    public void askPlayerForAction() {
        HashMap<Integer, String> actionInputs = new HashMap<>();
        actionInputs.put(1, "Look around");
        actionInputs.put(2, "Cast a spell");
        actionInputs.put(3, "Hide");
        if (player.hasAnyPotion()) {
            actionInputs.put(4, "Use a potion");
        }
        if (player.hasWeapon()) {
            int choiceNumber = 4;
            if (player.hasAnyPotion())  { choiceNumber = 5;}
            actionInputs.put(choiceNumber, "Attack with your weapon");
        }
        String choice = inputParser.getNumberToStringInput("What do you want to do?", actionInputs, "to");
        switch (choice) {
            case "Look around":
                lookAround();
                break;
            case "Cast a spell":
                castSpell();
                break;
            case "Hide":
                player.giveEffect(EffectType.HIDE, new ActiveEffect(1, 0.75));
                break;
            case "Use a potion":
                player.chooseAndConsumePotion();
                break;
            case "Attack with your weapon":
                player.attack(enemy);
                break;
        }
    }

    public boolean getFightEndCondition() {
        return (player.isAlive() && enemy.isAlive());
    }

    public void enemyAttack() {
        if (roundNumber % enemy.getAttackDelay() == 0) {
            enemy.act();
        }
    }

    public void nextRound() {
        enemy.finishRound();
        player.finishRound();
        roundNumber += 1;
    }

    public void lookAround() {
        double random = Math.random();
        if (random < 0.5) {
            PotionType potionType =  generatePotionType();
            display.announceDiscovery("Good job, you have found a " + potionType.toString() + "!");
            player.addPotion(new Potion(game, player, potionType));
        }
        else if (random < 0.9) {
            ItemType randomItemType = generateItemType();
            display.announceDiscovery("You have found an item. It looks like a " + randomItemType.toString());
            level.addItem(new Item(randomItemType, 0, 0, 0));
        }
        else {
            display.announceFail("Unfortunately, you haven't found anything.");
        }
    }

    public void castSpell() {
        String spellChoice = inputParser.getNumberToStringInput("What spell do you want to use?", spellInputs, "for");
        List<Item> items;
        switch(spellChoice) {
            case "Wingardium Leviosa":
                items = level.getItems();
                if (items.isEmpty()) {
                    display.announceFail("You haven't found any item to levitate. Try looking around.");
                    askPlayerForAction();
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
                items = level.getItems();
                if (items.isEmpty()) {
                    display.announceFail("You haven't found any item to engorge. Try looking around.");
                    askPlayerForAction();
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
                if (level instanceof Level2) {
                    ((Accio) player.getKnownSpells().get(spellChoice)).cast(Weapon.BASILISK_FANG);
                }
                break;
            case "Expecto Patronum":
                if (level instanceof Level3) {
                    if (((Expectopatronum) player.getKnownSpells().get(spellChoice)).cast()) {
                        ((Level3) level).setExpectoPatronumUsed();
                    }
                }
                else { display.displayInfo("This spell is useless here."); }
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
        List<Item> items = level.getItems();
        HashMap<Integer, String> itemInputs = new HashMap<>();
        for (int itemIndex = 0; itemIndex < items.size() ; itemIndex++) {
            itemInputs.put(itemIndex, items.get(itemIndex).getItemType().toString());
        }
        return itemInputs;
    }

    public ItemType generateItemType() {
        // I used weighted randomness instead of generating a random number from 0 to 1 and using a lot of ifs so that if I add an item I don't need to
        // change the other numbers
        // Inspired from https://stackoverflow.com/questions/6737283/weighted-randomness-in-java

        double totalWeight = 0.0;
        List<ItemType> possibleItemTypes = level.getPossibleItemTypes();

        for (ItemType pt : level.getPossibleItemTypes()) {
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
}

package Core.Levels.Essentials;

import Core.Characters.AbstractEnemy;
import Core.Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Core.Game.Game;
import Core.Items.Item;
import Core.Items.ItemType;
import Core.Items.Weapon;
import Core.Levels.Level;
import Core.Levels.Level2;
import Core.Levels.Level3;
import Core.Magic.Spells.*;
import Core.Magic.ActiveEffect;
import Core.Magic.EffectType;
import Core.Magic.Potion;
import Core.Magic.PotionType;
import Core.Magic.SimpleSpell;
import Core.Magic.Spell;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    }

    public void start() {
        displayBattleStartMessage();
        while (getBattleContinueCondition()) {
            display.displayHP(player, true);
            display.displayHP(enemy, false);
            askPlayerForAction();
            enemyAttack();
            finishRound();
        }
        if (!player.isAlive()) {
            level.fail();
        }
        else {
            displayBattleWinMessage();
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
                if (player.hasEffect(EffectType.DISARM)) {
                    display.announceFail("You cannot cast a spell because you are disarmed");
                    askPlayerForAction();
                }
                else {
                    castSpell();
                }
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

    public boolean getBattleContinueCondition() {
        return (!getBattleLossCondition() && !getBattleWinCondition());
    }

    public boolean getBattleLossCondition() {
        return (!player.isAlive());
    }

    public boolean getBattleWinCondition() {
        return (!enemy.isAlive());
    }

    public void enemyAttack() {
        if (roundNumber % enemy.getAttackDelay() == 0) {
            enemy.act();
        }
    }

    public void finishRound() {
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
            level.addItem(new Item(randomItemType));
        }
        else {
            display.announceFail("Unfortunately, you haven't found anything.");
        }
    }

    public void castSpell() {
        String spellChoice = inputParser.getNumberToStringInput("What spell do you want to use?", spellInputs, "for");
        Spell spell = player.getKnownSpells().get(spellChoice);
        if (spellChoice.equals("Accio")) {
            if (level instanceof Level2) {
                ((Accio) spell).cast(Weapon.BASILISK_FANG);
            }
        }
        else if (spellChoice.equals("Expecto Patronum")) {
            if (level instanceof Level3) {
                if (((Expectopatronum) spell).cast()) {
                    ((Level3) level).setExpectoPatronumUsed();
                    enemy.die();
                }
            }
            else { display.displayInfo("This spell is useless here."); }
        }
        else if (spell instanceof ItemSpell) {
            List<Item> items = level.getItems();
            if (items.isEmpty()) {
                display.announceFail("You haven't found any item. Try looking around.");
                askPlayerForAction();
            }
            else {
                HashMap<Integer, String> itemInputs = getItemInputs();
                display.displayInfo(itemInputs.toString());
                int itemIndex = inputParser.getNumberInput("Choose an item " + ((ItemSpell) spell).getStringForItem(), itemInputs, "for") - 1;
                Item item = items.get(itemIndex);
                if (spell instanceof WingardiumLeviosa) {
                    if (((WingardiumLeviosa) spell).cast(item, enemy)) {
                        items.remove(item);
                    }
                }
                else if (spell instanceof Engorgio) {
                    ((Engorgio) spell).cast(item);
                }
                else if (spell instanceof Reducto) {
                    if (((Reducto) spell).cast(item, enemy)) {
                        items.remove(item);
                    }
                }
            }
        }
        else if (player.getKnownSpells().get(spellChoice) instanceof SimpleSpell) {
            if (player.getEffectProbability(EffectType.UNDER_CONTROL)) {
                ((SimpleSpell) spell).cast(player);
            }
            else {
                ((SimpleSpell) spell).cast(enemy);
            }
        }
    }

    public HashMap<Integer, String> getSpellInputs() {
        HashMap<Integer, String> spellInputs = new HashMap<>();
        int i = 1;
        LinkedHashMap<String, Spell> playerKnownSpells = player.getKnownSpells();
        for (String spellName : playerKnownSpells.keySet()) {
            spellInputs.put(i, spellName);
            i++;
        }
        return spellInputs;
    }

    public HashMap<Integer, String> getItemInputs() {
        List<Item> items = level.getItems();
        HashMap<Integer, String> itemInputs = new HashMap<>();
        for (int itemIndex = 1; itemIndex < items.size() + 1 ; itemIndex++) {
            itemInputs.put(itemIndex, items.get(itemIndex - 1).getItemType().toString());
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

    public void displayBattleStartMessage() {
        if (enemy.getCustomBattleStartMessage() != null) {
            display.displayQuote(enemy, enemy.getCustomBattleStartMessage());
        }
        display.displayInfo("You have started a battle against " + enemy.getName());
    }

    public void displayBattleWinMessage() {
        display.announceSuccess("Well done, you have killed " + enemy.getName());
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}

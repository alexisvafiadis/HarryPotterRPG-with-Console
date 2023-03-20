package Levels;

import Characters.AbstractEnemy;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Items.Item;
import Items.ItemType;
import Game.Game;
import Items.Weapon;
import Potions.ActiveEffect;
import Potions.EffectType;
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
    protected String name;
    protected String place;
    protected boolean outdoors;
    protected List<Item> items;
    protected List<ItemType> possibleItemTypes;

    //Constants
    protected final double HP_UPGRADE = 20;
    protected final double SPELL_DAMAGE_UPGRADE = 0.2;
    protected final double DAMAGE_RESISTANCE_UPGRADE = 0.2;
    protected final double ACCURACY_UPGRADE = 0.25;


    public Level(Game game, String name, String place, int number, boolean outdoors) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        this.player = game.getPlayer();

        this.name = name;
        this.place = place;
        this.number = number;
        this.outdoors = outdoors;
    }

    public abstract void introduce();

    public void start() {
        setPossibleItemTypes();
        items = new ArrayList<>();
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

    public void giveLevelInfo() {
        display.displayInfo("--- Level " + number + " : " + name + " ---");
        display.displayInfo("You arrive at " + place);
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
                game.getPlayer().upgradeDamage(SPELL_DAMAGE_UPGRADE);
                break;
            case "Damage Resistance":
                game.getPlayer().upgradeResistance(DAMAGE_RESISTANCE_UPGRADE);
                break;
            case "Accuracy":
                game.getPlayer().upgradeAccuracy(ACCURACY_UPGRADE);
                break;
        }
    }

    public List<ItemType> getPossibleItemTypes() {
        return possibleItemTypes;
    }

    public void setPossibleItemTypes() {
        possibleItemTypes = new ArrayList<>(Arrays.asList(ItemType.values()));
        if (outdoors) { possibleItemTypes.removeIf(it -> (it.getWhere() == 0)); }
        else { possibleItemTypes.removeIf(it -> (it.getWhere() == 1)); }
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

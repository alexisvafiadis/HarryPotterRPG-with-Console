package Core.Levels;

import Core.Characters.AbstractEnemy;
import Core.Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Core.Items.Item;
import Core.Items.ItemType;
import Core.Game.Game;
import Core.Levels.Essentials.Battle;

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
    protected boolean levelCompleted;

    //Constants
    protected final double HP_UPGRADE = 35;
    protected final double SPELL_DAMAGE_UPGRADE = 0.27;
    protected final double DAMAGE_RESISTANCE_UPGRADE = 0.27;
    protected final double ACCURACY_UPGRADE = 0.32;

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

    public void initialize() {
        levelCompleted = false;
        setPossibleItemTypes();
        items = new ArrayList<>();
    }

    public void start() {
        initialize();
        introduce();
    }

    public abstract void conclude();

    public void fail() {
        display.announceFail("You failed this level. Try again!");
        start();
    }

    /*
    public void fail(Class<? extends Level> levelClass) {
        display.announceFail("You failed this level. Try again!");
        try {
            Level currentLevel = levelClass.getDeclaredConstructor(Game.class).newInstance(game);
            game.setLevel(currentLevel);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
     */

    public void finish() {
        if (!levelCompleted) {
            conclude();
            display.announceSuccess("Congratulations, you have completed the level " + number + "!");
            askForUpgrade();
            levelCompleted = true;
        }
    }

    public void wishGoodLuck() {display.displayInfo("Good luck!"); }

    public void giveLevelInfo() {
        display.displayInfo("--- Level " + number + " : " + name + " ---");
        display.displayInfo("You arrive at the " + place);
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
        if (outdoors) { possibleItemTypes.removeIf(it -> (it.getWhere() != 1)); }
        else { possibleItemTypes.removeIf(it -> (it.getWhere() != 0)); }
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

    public void askForDirections() {
        HashMap<Integer, String> directionInputs = new HashMap<>();
        directionInputs.put(1, "move forwards");
        directionInputs.put(2, "move backwards");
        directionInputs.put(3, "move to the left");
        directionInputs.put(4, "move to the right");
        String directionChoice = inputParser.getNumberToStringInput("Where do you want to go?", directionInputs, "to");
        boolean canMove = false;
        switch (directionChoice) {
            case "move forwards":
                canMove = player.moveForwards();
                break;
            case "move backwards":
                canMove = player.moveBackwards();
                break;
            case "move to the left":
                canMove = player.moveLeft();
                break;
            case "move to the right":
                canMove = player.moveRight();
                break;
        }
        if (canMove) {
            display.displayInfo("You successfully " + directionChoice);
        } else {
            display.announceFail("You cannot move there, sorry. Choose another direction");
        }
    }

    public void startBattle(AbstractEnemy enemy) {
        new Battle(game, this, player, enemy).start();
    }
}

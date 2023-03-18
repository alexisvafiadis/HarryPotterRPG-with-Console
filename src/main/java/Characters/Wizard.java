package Characters;

import Console.Display;
import Console.InputParser;
import Extras.Pet;
import Game.Game;
import Houses.Gryffindor;
import Houses.House;
import Houses.Slytherin;
import Potions.Potion;
import Spells.Spell;
import Wands.Wand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Wizard extends Character{
    //Global attributes
    Game game;
    Display display;
    InputParser inputParser;

    //Specific attributes
    String name;
    Pet pet;
    Wand wand;
    House house;
    HashMap<String, Spell> knownSpells;
    List<Potion> potions;

    //Stats
    final double DEFAULT_MAX_HP = 100;
    double damageMultiplier = 1;
    double accuracy = 0.8;
    double resistance = 1;

    //Potion effects
    boolean strengthEffect;
    double strengthMultiplier;
    int nbOfStrengthRoundsLeft;
    boolean resistanceEffect;
    double resistanceMultiplier;
    int nbOfResistanceRoundsLeft;
    boolean invisibilityEffect;
    boolean speedEffect;

    public Wizard(Game game) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        Display display;
        InputParser inputParser;
        this.knownSpells = new HashMap<>();
        this.potions = new ArrayList<>();
        setMaxHP(DEFAULT_MAX_HP);
        setPhysicalDamage(5);
    }

    @Override
    public void spawn(double positionX, double positionY, double positionZ) {
        super.spawn(positionX, positionY, positionZ);
        strengthEffect = false;
        resistanceEffect = false;
        invisibilityEffect = false;
        speedEffect = false;
    }

    public double amplifyDamage(double damage) {
        if (house instanceof Slytherin) {
            damage = damage * ((Slytherin) house).getDamageMultiplier();
        }
        if (strengthEffect) {
            damage = damage * strengthMultiplier;
        }
        return damage;
    }

    public double defendDamage(double damage) {
        if (house instanceof Gryffindor) {
            damage = damage * ((Gryffindor) house).getDamageResistance();
        }
        if (resistanceEffect) {
            damage = damage * resistanceMultiplier;
        }
        return damage;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Wand getWand() {
        return wand;
    }

    public void setWand(Wand wand) {
        this.wand = wand;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public HashMap<String, Spell> getKnownSpells() {
        return knownSpells;
    }

    public void learnSpell(Spell spell) {
        this.knownSpells.put(spell.getName(), spell);
    }

    public boolean knowsSpell(String spellName) { return knownSpells.containsKey(spellName); }

    public void addPotion(Potion potion) { this.potions.add(potion); }

    public void addPotions(Potion potion, int amount) {
        for (int i = 0; i < amount; i++) {
            this.potions.add(potion);
        }
    }

    public void chooseAndConsumePotion() {
        HashMap<Integer, String> potionInputs = new HashMap<>();
        int i = 0;
        for (Potion potion : potions) {
            potionInputs.put(i, potion.getPotionType().toString());
            i += 1;
        }
        int choice = inputParser.getNumberInput("Which potion do you want to use?", potionInputs, "for");
        consumePotion(potions.get(choice));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void upgradeDamage(double upgrade) {
        display.announceReward("Your damage has been upgraded!");
        damageMultiplier *= upgrade;
    }

    public void upgradeHP(double upgrade) {
        display.announceReward("Your HP has been upgraded!");
        setMaxHP(getMaxHP() + upgrade);
    }

    public void upgradeAccuracy(double upgrade) {
        display.announceReward("Your accuracy has been upgraded!");
        accuracy += upgrade;
    }

    public void upgradeResistance(double upgrade) {
        display.announceReward("Your damage resistance has been upgraded!");
        resistance += upgrade;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getResistance() {
        return resistance;
    }

    public void consumePotion(Potion potion) {
        int potionIndex = potions.indexOf(potion);
        potions.get(potionIndex).use();
        potions.remove(potion);
    }

    public boolean hasAnyPotion() {
        return (potions.isEmpty());
    }

    public void boostStrength(int duration, double amplifier) {
        nbOfStrengthRoundsLeft = duration;
        strengthMultiplier = amplifier;
        strengthEffect = true;
    }

    public void boostResistance(int duration, double amplifier) {
        nbOfResistanceRoundsLeft = duration;
        resistanceMultiplier = amplifier;
        resistanceEffect = true;
    }

    @Override
    public void attack(Character victim) {

    }

    @Override
    public void die() {
        game.getCurrentLevel().fail();
        game.getCurrentLevel().start();
    }

    @Override
    public void attackedByExpelliarmus() {

    }

    @Override
    public void finishRound() {
        super.finishRound();
        if (strengthEffect) {nbOfStrengthRoundsLeft -= 1;}
        if (resistanceEffect) {nbOfResistanceRoundsLeft -= 1; }
    }
}

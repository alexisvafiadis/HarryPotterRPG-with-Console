package Characters;

import Extras.Pet;
import Game.Game;
import Houses.Gryffindor;
import Houses.House;
import Houses.Slytherin;
import Potions.Potion;
import Spells.Spell;
import Tools.ProjectTools;
import Wands.Wand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Wizard extends Character{
    Pet pet;
    Wand wand;
    House house;
    HashMap<String, Spell> knownSpells;
    List<Potion> potions;
    double HP;
    Game game;
    double damage = 10;
    double maxHP = 100;
    double accuracy = 0.8;
    double resistance;
    boolean strengthEffect = false;
    double STRENGTH_MULTIPLIER = 1.25;
    boolean invisibilityEffect = false;
    boolean resistanceEffect = false;
    double RESISTANCE_MULTIPLIER = 0.75;
    boolean speedEffect = false;
    String name;

    public Wizard(Game game) {
        this.game = game;
        this.knownSpells = new HashMap<>();
    }

    public double amplifyDamage(double damage) {
        if (house instanceof Slytherin) {
            damage = damage * ((Slytherin) house).getDamageMultiplier();
        }
        if (strengthEffect) {
            damage = damage * STRENGTH_MULTIPLIER;
        }
        return damage;
    }

    public double defendDamage(double damage) {
        if (house instanceof Gryffindor) {
            damage = damage * ((Gryffindor) house).getDamageResistance();
        }
        if (resistanceEffect) {
            damage = damage * RESISTANCE_MULTIPLIER;
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

    public void addPotion(Potion potion) { this.potions.add(potion); }

    public void addPotions(Potion potion, int amount) {
        for (int i = 0; i < amount; i++) {
            this.potions.add(potion);
        }
    }

    public void choosePotion() {
        HashMap<Integer, String> validInputs = new HashMap<>();
        int i = 0;
        for (Potion potion : potions) {
            validInputs.put(i, potion.getName());
            i += 1;
        }
        int choice = ProjectTools.getNumberInput(game.getSc(), "Which potion do you want to use?", validInputs, "for");
        potions.get(choice).use();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void upgradeDamage(double upgrade) {
        game.announceReward("Your damage has been upgraded!");
        damage += upgrade;
    }

    public void upgradeHP(double upgrade) {
        game.announceReward("Your HP has been upgraded!");
        maxHP += upgrade;
    }

    public void upgradeAccuracy(double upgrade) {
        game.announceReward("Your accuracy has been upgraded!");
        accuracy += upgrade;
    }

    public void upgradeResistance(double upgrade) {
        game.announceReward("Your damage resistance has been upgraded!");
        resistance += upgrade;
    }

    public double getDamage() {
        return damage;
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

    public void heal(double hp_restore) {
        HP = HP + hp_restore;
    }

    @Override
    public void attack(Character victim) {

    }

    @Override
    public void die() {
        game.getCurrentLevel().fail();
        game.getCurrentLevel().start();
    }

}

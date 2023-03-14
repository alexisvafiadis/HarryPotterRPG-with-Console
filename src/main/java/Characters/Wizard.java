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
    List<Spell> knownSpells;
    List<Potion> potions;
    double HP;
    Game game;
    double damage = 10;
    double maxHP = 100;
    double accuracy = 0.8;
    double resistance;
    String name;

    public Wizard(Game game) {
        this.game = game;
        this.knownSpells = new ArrayList<>();
    }

    public void attack(Character character, Spell spell) {
        double damage = 0;
        if (house instanceof Slytherin) {
            damage = damage * ((Slytherin) house).getDamageMultiplier();
        }
        character.damage(damage);
    }

    public double defend(double damage) {
        if (house instanceof Gryffindor) {
            return damage * ((Gryffindor) house).getDamageResistance();
        }
        else {
            return damage;
        }
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

    public List<Spell> getKnownSpells() {
        return knownSpells;
    }

    public void learnSpell(Spell spell) {
        this.knownSpells.add(spell);
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
        validInputs.put(1, "Look around");
        validInputs.put(2, "Attack");
        validInputs.put(3, "Hide");
        String choice = ProjectTools.getNumberToStringInput(game.getSc(), "What do you want to do?", validInputs, "to");
        switch (choice) {
            case "Look around":
                double random = Math.random();
                if (random < 0.3) {
                    game.displayInfo("Good job, you have found a potion!");


                } else if (random < 0.6) {
                    game.displayInfo("Good job, you have found an item. It looks like a ");

                } else if (random < 0.9) {
                    game.displayInfo("");
                } else {
                    game.displayInfo("Unfortunately, you haven't found anything.");
                }
                break;
        }
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

    @Override
    public double getMAX_HP() {
        return maxHP;
    }
}

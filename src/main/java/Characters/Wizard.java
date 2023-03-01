package Characters;

import Extra.Pet;
import Houses.House;
import Potions.Potion;
import Spells.Spell;
import Wands.Wand;

import java.util.List;

public class Wizard extends Character{
    Pet pet;
    Wand wand;
    House house;
    List<Spell> knownSpells;
    List<Potion> potions;
    double hp;

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

    public void setKnownSpells(List<Spell> knownSpells) {
        this.knownSpells = knownSpells;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }

    @Override
    public void die() {

    }
}

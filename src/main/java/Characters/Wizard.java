package Characters;

import Potions.EffectType;
import Characters.Characteristics.Pet;
import Game.Game;
import Characters.Characteristics.House;
import Potions.Potion;
import Spells.Spell;
import Items.Wand;

import java.util.HashMap;
import java.util.List;

public class Wizard extends Character{
    //Specific attributes
    String name;
    Pet pet;
    Wand wand;
    House house;
    HashMap<String, Spell> knownSpells;
    List<Potion> potions;

    //Stats
    double spellDamageMultiplier = 1;
    double accuracy = 0.8;
    double resistance = 1;

    public Wizard(Game game) {
        super(game, 100, 5, 0.95,null,'B',1);
    }

    @Override
    public void spawn(int positionX, int positionY) {
        super.spawn(positionX, positionY);
        setWeapon(null);
    }

    public double amplifyDamage(double damage) {
        if (hasEffect(EffectType.STRENGTH)) {
            damage = damage * activeEffects.get(EffectType.STRENGTH).getValue();
        }
        return damage;
    }

    public double amplifySpellDamage(double damage) {
        damage = damage * spellDamageMultiplier * house.getSPELL_DAMAGE_MULTIPLIER();
        return damage;
    }

    public double defendDamage(double damage) {
        if (hasEffect(EffectType.RESISTANCE)) {
            damage = damage * activeEffects.get(EffectType.RESISTANCE).getValue();
        }
        damage = damage * house.getDAMAGE_VULNERABILITY();
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
        spellDamageMultiplier += upgrade;
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

    public double getAccuracy() {
        return accuracy * house.getACCURACY_MULTIPLIER();
    }

    public void consumePotion(Potion potion) {
        int potionIndex = potions.indexOf(potion);
        potions.get(potionIndex).use();
        potions.remove(potion);
    }

    public boolean hasAnyPotion() {
        return (!potions.isEmpty());
    }

    @Override
    public void die() {
        game.getCurrentLevel().fail();
        game.getCurrentLevel().start();
    }

    @Override
    public void attackedByExpelliarmus() {

    }
}

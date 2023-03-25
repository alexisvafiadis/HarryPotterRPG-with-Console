package Characters;

import Magic.EffectType;
import Characters.Characteristics.Pet;
import Game.Game;
import Characters.Characteristics.House;
import Magic.Potion;
import Magic.Spell;
import Items.Wand;

import java.util.*;

public class Wizard extends Character{
    //Specific attributes
    private String name;
    private Pet pet;
    private Wand wand;
    private House house;
    private LinkedHashMap<String, Spell> knownSpells;
    private List<Potion> potions;
    private boolean againstDeathEaters;

    //Stats
    private double spellDamageMultiplier = 1;
    private double accuracy = 0.8;
    private double resistance = 1;

    public Wizard(Game game) {
        super(game, 100, 5, 0.95,null,'Y',1);
        knownSpells = new LinkedHashMap<>();
        potions = new ArrayList<>();
    }

    @Override
    public void spawn() {
        super.spawn();
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

    public LinkedHashMap<String, Spell> getKnownSpells() { return knownSpells; }

    public void learnSpell(Spell spell) {
        if (!knownSpells.containsKey(spell.getName())) {
            this.knownSpells.put(spell.getName(), spell);
        }
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
        display.displayInfo("You have been killed.");
        game.getCurrentLevel().fail();
        game.getCurrentLevel().start();
    }

    public boolean isAgainstDeathEaters() {
        return againstDeathEaters;
    }

    public void setAgainstDeathEaters(boolean againstDeathEaters) {
        this.againstDeathEaters = againstDeathEaters;
    }
}

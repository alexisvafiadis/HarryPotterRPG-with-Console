package Characters;

import Game.Game;
import Items.Weapon;
import Potions.EffectType;
import Spells.SimpleSpell;
import Spells.Spell;

import java.util.HashMap;
import java.util.Map;

public abstract class EnemyWizard extends AbstractEnemy {
    protected Map<SimpleSpell, Integer> spellWeights;
    protected double spellDamageMultiplier;
    protected double defaultMasteryScoreMultiplier; //multiplier to raise the accuracy without needing to train the wizard
    protected int totalSpellWeight;

    public EnemyWizard(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, char charTile, int moveStep, double spellDamageMultiplier, double defaultMasteryScoreMultiplier) {
        super(game, maxHP, physicalDamage, vulnerabilityToMagic, null, charTile, moveStep, 1);
        spellWeights = new HashMap<>();
        this.spellDamageMultiplier = spellDamageMultiplier;
        this.defaultMasteryScoreMultiplier = defaultMasteryScoreMultiplier;
    }

    public double amplifySpellDamage(double damage) {
        damage = damage * spellDamageMultiplier;
        return damage;
    }

    @Override
    public void act() {
        if (hasEffect(EffectType.DISARM)) {
            display.displayInfo(getName() + " cannot cast a spell because they are disarmed");
        }
        else {
            SimpleSpell spell = generateSpell();
            spell.cast(game.getPlayer());
        }
    }

    public SimpleSpell generateSpell() {
        SimpleSpell simpleSpell = null;
        double r = Math.random() * totalSpellWeight;
        for (SimpleSpell ss : spellWeights.keySet()) {
            r -= spellWeights.get(ss);
            if (r <= 0.0) {
                simpleSpell = ss;
                break;
            }
        }
        return simpleSpell;
    }

    public void calculateTotalSpellWeight() {
        totalSpellWeight = 0;
        for (int w : spellWeights.values()) {
            totalSpellWeight += w;
        }
    }

    @Override
    public void spawn() {
        super.spawn();
        calculateTotalSpellWeight(); //to avoid adding this function in every child of enemywizard after adding the spells
    }

    public void addSpell(SimpleSpell spell, Integer weight) { spellWeights.put(spell, weight); }

    public double getDefaultMasteryScoreMultiplier() {
        return defaultMasteryScoreMultiplier;
    }
}
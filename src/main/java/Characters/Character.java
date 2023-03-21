package Characters;

import Potions.EffectType;
import Console.Display;
import Console.InputParser;
import Items.Weapon;
import Game.Game;
import Potions.ActiveEffect;

import java.util.HashMap;
import java.util.Map;

public abstract class Character {
    //Global attributes
    protected Game game;
    protected Display display;
    protected InputParser inputParser;

    //Specific attributes (need to set!)
    protected double maxHP;
    protected double physicalDamage;
    protected double vulnerabilityToMagic;//A coefficient that represents how likely a wizard can successfully cast a spell on the person
    protected Weapon weapon;

    //Live attributes
    protected double positionX;
    protected double positionY;
    protected double positionZ;
    protected double HP;
    protected boolean alive;
    protected boolean disarmed;
    protected boolean confused;
    protected boolean laughing;
    protected int nbOfLaughingRoundsLeft;
    protected int nbOfConfusionRoundsLeft;
    Map<EffectType, ActiveEffect> activeEffects;
    protected int nbOfDisarmedRoundsLeft;

    public void spawn(double positionX, double positionY, double positionZ) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        HP = maxHP;
        alive = true;
        activeEffects = new HashMap<>();
        confused = false;
        disarmed = false;
    }

    public void attack(Character target) {
        if (canAttack(target)) {
            double damage = getPhysicalDamage();
            if (hasWeapon()) {
                damage += weapon.getAttackDamage();
            }
            if (this instanceof Wizard) {
                damage = ((Wizard) this).amplifyDamage(damage);
                display.displayInfo("You have damaged " + target.getName());
            }
            if (target instanceof Wizard) {
                damage = ((Wizard) target).defendDamage(damage);
                display.displayInfo("You have been attacked by " + getName());
            }
            target.damage(damage);
        }
    }

    public void die() {
        if (this instanceof Wizard) {
            System.out.println("You have been killed.");
        }
        alive = false;
    }

    public void damage(double damage) {
        if (HP <= damage) { die();}
        else { HP = HP - damage; }
    }

    public void speak(String quote) {
        display.displayQuote(getName(), quote);
    }

    public double getHP() {
        return HP;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(double physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public double getVulnerabilityToMagic() {
        return vulnerabilityToMagic;
    }

    public void setVulnerabilityToMagic(double vulnerabilityToMagic) {
        this.vulnerabilityToMagic = vulnerabilityToMagic;
    }

    public void heal(double hp_restore) {
        HP = HP + hp_restore;
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract String getName();

    public boolean isDisarmed() {
        return disarmed;
    }

    public void setDisarmed(boolean disarmed) {
        this.disarmed = disarmed;
    }

    public boolean hasEffect(EffectType et) {return (activeEffects.containsKey(et));}

    public void giveEffect(EffectType effectType, ActiveEffect activeEffect) {
        activeEffects.put(effectType, activeEffect);
    }

    public void removeEffect(EffectType et) { activeEffects.remove(et); }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean hasWeapon() { return (weapon != null); }

    public abstract void attackedByExpelliarmus();

    public boolean canAttack(Character target) {
        if (getEffectProbability(EffectType.LAUGH)) {
            display.displayInfo(getName() + " couldn't attack because they are laughing");
            return false;
        }
        if (getEffectProbability(EffectType.CONFUSION)) {
            display.displayInfo(getName() + " couldn't attack because they are confused.");
            return false;
        }
        if (target.getEffectProbability(EffectType.HIDE)) {
            display.announceSuccess("Well done, " + getName() + " couldn't find you!");
            return false;
        }
        return true;
    }

    public boolean getEffectProbability(EffectType et) {
        return (activeEffects.containsKey(et) && Math.random() < activeEffects.get(et).getValue());
    }

    public void finishRound() {
        for (EffectType effectType : activeEffects.keySet()) {
            if (activeEffects.get(effectType).getNbOfRoundsLeft() == 1) {
                String start;
                if (this instanceof Wizard) {start = "You are "; } else { start = "He is ";}
                display.displayInfo(start + effectType.getEndMessage());
                activeEffects.remove(effectType);
            }
            else {
                activeEffects.get(effectType).reduceNbOfRounds();
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        this.inputParser = game.getInputParser();
        this.display = game.getDisplay();
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getPositionZ() {
        return positionZ;
    }
}

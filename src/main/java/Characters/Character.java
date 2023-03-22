package Characters;

import Levels.Essentials.LevelMap;
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
    protected LevelMap map;

    //Specific attributes (need to set!)
    protected double maxHP;
    protected double physicalDamage;
    protected double vulnerabilityToMagic;//A coefficient that represents how likely a wizard can successfully cast a spell on the person
    protected Weapon weapon;
    protected char charTile;
    protected int moveStep;

    //Live attributes
    protected int positionX;
    protected int positionY;
    protected double HP;
    protected boolean alive;
    protected boolean disarmed;
    Map<EffectType, ActiveEffect> activeEffects;

    public Character(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep) {
        this.game = game;
        this.display = game.getDisplay();
        this.inputParser = game.getInputParser();
        this.maxHP = maxHP;
        this.physicalDamage = physicalDamage;
        this.vulnerabilityToMagic = vulnerabilityToMagic;
        this.weapon = weapon;
        this.charTile = charTile;
        this.moveStep = moveStep;
    }

    public void spawn(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        HP = maxHP;
        alive = true;
        activeEffects = new HashMap<>();
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

    public LevelMap getMap() {
        return map;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public boolean moveForwards() { return moveTo(positionX, positionY + moveStep); }

    public boolean moveBackwards() {  return moveTo(positionX, positionY - moveStep); }

    public boolean moveRight() {  return moveTo(positionX + moveStep, positionY); }

    public boolean moveLeft() { return moveTo(positionX - moveStep, positionY); }

    public boolean moveTo(int positionX, int positionY) {
        boolean isPositionPossible = map.isPositionPossible(positionX, positionY);
        if (isPositionPossible) {
            this.positionX = positionX;
            this.positionY = positionY;
            map.setTile(positionX, positionY, charTile);
        }
        return isPositionPossible;

    }

}

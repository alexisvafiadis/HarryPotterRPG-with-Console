package Core.Characters;

import Core.Levels.Essentials.LevelMap;
import Core.Magic.EffectCategory;
import Core.Magic.EffectType;
import Console.Display;
import Console.InputParser;
import Core.Items.Weapon;
import Core.Game.Game;
import Core.Magic.ActiveEffect;

import java.util.HashMap;
import java.util.Iterator;
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
    protected Weapon currentWeapon;
    protected char charTile;
    protected Integer moveStep;

    //Live attributes
    protected Integer positionX;
    protected Integer positionY;
    protected double HP;
    protected boolean alive;
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

    public void spawn(int positionX, int positionY, LevelMap map) {
        spawn();
        this.map = map;
        moveTo(positionX, positionY);
    }

    public void spawn() {
        HP = maxHP;
        alive = true;
        activeEffects = new HashMap<>();
        currentWeapon = weapon;
    }

    public void attack(Character target) {
        if (canAttack(target)) {
            double damage = getPhysicalDamage();
            if (hasWeapon() && !hasEffect(EffectType.DISARM)) {
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
        alive = false;
    }

    public void damage(double damage) {
        if (HP <= damage) { die();}
        else { HP = HP - damage; }
    }

    public void speak(String quote) {
        display.displayQuote(this, quote);
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
        if ((maxHP - HP) < hp_restore) {
            HP = maxHP;
        }
        else {
            HP = HP + hp_restore;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract String getName();

    public boolean hasEffect(EffectType et) {return (activeEffects.containsKey(et));}

    public void giveEffect(EffectType effectType, ActiveEffect activeEffect) {
        if (hasEffect(effectType)) {
            display.displayInfo(game.getMessageStartBe(this) + " " +  effectType.getAlreadyAffectedMessage() +  ", so this was uneffective");
        }
        else {
            display.displayInfo(game.getMessageStartBe((this)) + " " + effectType.getStartMessage());
            activeEffects.put(effectType, activeEffect);
            if (effectType.equals(EffectType.DISARM)) {
                currentWeapon = null;
            }
        }
    }

    public void removeEffect(EffectType et) {
        actionBeforeEffectRemoval(et);
        activeEffects.remove(et);
    }

    public void actionBeforeEffectRemoval(EffectType et) {
        display.displayInfo(game.getMessageStartBe(this) + " " + et.getEndMessage());
        if (et.equals(EffectType.DISARM) && weapon != null) { currentWeapon = weapon; }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean hasWeapon() { return (weapon != null); }

    public boolean canDoSomething() {
        for (EffectType effectType : activeEffects.keySet()) {
            if (effectType.getEffectCategory().equals(EffectCategory.INABILITY) && (getEffectProbability(effectType))) {
                display.displayInfo(getName() + " couldn't do anything because they are " + effectType.getConsequenceMessage());
                return false;
            }
        }
        return true;
    }

    public boolean canAttack(Character target) {
        if (canDoSomething()) {
            for (EffectType effectType : target.getActiveEffects().keySet()) {
                if (effectType.getEffectCategory().equals(EffectCategory.PROTECTION) && (getEffectProbability(effectType))) {
                        display.displayInfo(getName() + " couldn't attack " + target.getName() + " because they are " + effectType.getConsequenceMessage());
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
        /*
        if (getEffectProbability(EffectType.LAUGH)) {

        }
        else if (getEffectProbability(EffectType.CONFUSION)) {
            display.displayInfo(getName() + " couldn't do anything because they are confused.");
            return false;
        }
        else if (getEffectProbability(EffectType.STUN)) {
            display.displayInfo(getName() + " couldn't do anything because they are momentarily stunned");
            return false;
        }
        else if (target.getEffectProbability(EffectType.HIDE)) {
            display.announceSuccess("Well done, " + getName() + " couldn't find you!");
            return false;
        }
        else if (target.getEffectProbability(EffectType.SHIELD)) {
            display.displayInfo(target.getName() + "'s shield protected them from " + getName() +"'s attack");
            return false;
        }
         */
    }

    public boolean getEffectProbability(EffectType et) {
        return (activeEffects.containsKey(et) && Math.random() < activeEffects.get(et).getValue());
    }

    public void finishRound() {
        if (hasEffect(EffectType.EXCRUCIATING_PAIN)) {
            double damage = activeEffects.get(EffectType.EXCRUCIATING_PAIN).getValue() / activeEffects.get(EffectType.EXCRUCIATING_PAIN).getNbOfRoundsLeft();
            display.displayInfo(getName() + " " + EffectType.EXCRUCIATING_PAIN.getConsequenceMessage());
            damage(damage);
        }
        if (hasEffect(EffectType.BURN)) {
            double damage = activeEffects.get(EffectType.BURN).getValue();
            display.displayInfo(getName() + " " + EffectType.BURN.getConsequenceMessage());
            damage(damage);
        }
        Iterator<Map.Entry<EffectType, ActiveEffect>> it = activeEffects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<EffectType, ActiveEffect> entry = it.next();
            if (entry.getValue().getNbOfRoundsLeft() == 0) {
                actionBeforeEffectRemoval(entry.getKey());
                it.remove();
            }
        }
        for (EffectType effectType : activeEffects.keySet()) {
            activeEffects.get(effectType).reduceNbOfRounds();
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

    public boolean moveTo(Integer positionX, Integer positionY) {
        if (this.positionX != null && this.positionY != null) {
            map.clearTile(this.positionX, this.positionY);
        }
        boolean isPositionAvailable = map.isPositionAvailable(positionX, positionY);
        if (isPositionAvailable) {
            this.positionX = positionX;
            this.positionY = positionY;
            map.setTile(positionX, positionY, charTile);
        }
        return isPositionAvailable;
    }

    public Map<EffectType, ActiveEffect> getActiveEffects() {
        return activeEffects;
    }

    public ActiveEffect getActiveEffect(EffectType effectType) {
        return activeEffects.get(effectType);
    }

}

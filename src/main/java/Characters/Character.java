package Characters;

import Console.Display;
import Console.InputParser;
import Items.Weapon;
import Game.Game;

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
    protected int nbOfConfusionRoundsLeft;

    public void spawn(double positionX, double positionY, double positionZ) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        HP = maxHP;
        alive = true;
        confused = false;
        disarmed = false;
    }

    public void attack(Character victim) {
        if (canAttack()) {
            double damage = getPhysicalDamage();
            display.displayInfo(String.valueOf(damage));
            if (hasWeapon()) {
                damage += weapon.getAttackDamage();
            }
            if (this instanceof Wizard) {
                damage = ((Wizard) this).amplifyDamage(damage);
                display.displayInfo("You have damaged " + victim.getName());
            }
            if (victim instanceof Wizard) {
                damage = ((Wizard) victim).defendDamage(damage);
                display.displayInfo("You have been attacked by " + getName());
            }
            victim.damage(damage);
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean hasWeapon() { return (weapon != null); }

    public abstract void attackedByExpelliarmus();

    public boolean canAttack() {
        if (!isConfused()) { return true;}
        if (Math.random() < 0.5) {
            display.displayInfo(getName() + " couldn't attack because they are confused.");
            return false;
        }
        else {
            display.displayInfo(getName() + " managed to attack despite being confused.");
            return true;
        }
    }

    public boolean isConfused() {
        return confused;
    }

    public void setConfused(boolean confused) {
        this.confused = confused;
    }

    public void finishRound() {
        if (confused) { nbOfConfusionRoundsLeft -= -1; }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        this.inputParser = game.getInputParser();
        this.display = game.getDisplay();
    }
}

package Characters;

import Console.Display;
import Console.InputParser;
import Extras.Weapon;
import Game.Game;
import Houses.Gryffindor;
import Houses.House;
import Houses.Hufflepuff;
import Houses.Slytherin;
import Spells.Spell;

public abstract class Character {
    Game game;
    Display display;
    InputParser inputParser;
    double HP;
    boolean alive;
    double positionX;
    double positionY;
    double positionZ;
    double maxHP;
    boolean disarmed;
    double physicalDamage;
    Weapon weapon;
    boolean confused;
    int nbOfConfusionRoundsLeft;

    public abstract void attack(Character victim) ;

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

    public void spawn(double positionX, double positionY, double positionZ) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        HP = maxHP;
        alive = true;
        confused = false;
        disarmed = false;
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

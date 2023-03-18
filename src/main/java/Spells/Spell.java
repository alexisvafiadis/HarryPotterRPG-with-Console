package Spells;

import Characters.Wizard;
import Game.Game;

public abstract class Spell {
    String name;
    int range;
    float cooldown;
    int nbOfUses;
    double learningExponent;
    double defaultMasteryScore;
    Game game;
    Wizard wizard;

    public Spell(Game game, Wizard wizard, String name, int range, float cooldown, double learningExponent, double defaultMasteryScore) {
        this.game = game;
        this.wizard = wizard;
        this.name = name;
        this.range = range;
        this.cooldown = cooldown;
        this.learningExponent = learningExponent;
        this.defaultMasteryScore = defaultMasteryScore;
        nbOfUses = 0;
    }

    //Function that returns a number between 0 and 1 signifying how much the owner has mastered the spell
    //I will then use it as an accuracy (a probability of successfully casting the spell)
    public double getMasteryScore() {
        if (nbOfUses < 2) {
            return defaultMasteryScore;
        }
        else {
            //Mathematical function that increases slower and slower with a maximum of 1 to imitate a learning curve
            return 1 - Math.exp(-1 * (Math.pow(nbOfUses, learningExponent)));
        }
    }

    public boolean isCastSuccessful(Wizard wizard) {
        boolean b = (Math.random() < getMasteryScore() * wizard.getAccuracy());
        if (!b) { game.getDisplay().announceFail("Nice try. Unfortunately, you failed to cast the spell " + getName()); }
        return b;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public int getNbOfUses() {
        return nbOfUses;
    }

    public Game getGame() {
        return game;
    }

    public abstract void tryForFirstTime();
}
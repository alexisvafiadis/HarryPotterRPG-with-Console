package Spells;

import Characters.Wizard;
import Game.Game;

public class Spell {
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
            /*
            switch(nbOfUses) {
                case 0:
                    return 0.4;
                case 1:
                    return 0.6;
                case 2:
                    return 0.75;
                case 3:
                    return 0.825;
                case 4:
                    return 0.875;
                case 5:
                    return 0.915;
                case 6:
                    return 0.93;
                case 7:
                    return 0.945;
                case 8:
                    return 0.96;
                case 9:
                    return 0.985;
                default:
                    return 1;
            }
             */
    }

    public boolean isCastSuccessful(Wizard wizard) {
        boolean b = (Math.random() < getMasteryScore() * wizard.getAccuracy());
        if (!b) { game.announceFail("Nice try. Unfortunately, you failed to cast the spell " + getName()); }
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
}
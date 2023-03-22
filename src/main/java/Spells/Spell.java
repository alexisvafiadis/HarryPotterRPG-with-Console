package Spells;

import Characters.Character;
import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Game.Game;

public abstract class Spell {
    protected String name;
    protected int range;
    protected float cooldown;
    protected int nbOfUses;
    private double learningExponent;
    private double defaultMasteryScore;
    protected Game game;
    protected Display display;
    protected InputParser inputParser;
    protected Wizard wizard;

    public Spell(Game game, Wizard wizard, String name, int range, float cooldown, double learningExponent, double defaultMasteryScore) {
        this.game = game;
        this.wizard = wizard;
        this.display = game.getDisplay();
        this.inputParser = game.getInputParser();
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

    public boolean isCastSuccessful(Wizard wizard, Character target) {
        double probability = getMasteryScore() * wizard.getAccuracy();
        if (target != null) { probability = probability * target.getVulnerabilityToMagic(); }
        boolean b = (Math.random() < probability) ;
        if (!b) { display.announceFail("Nice try. Unfortunately, you failed to cast the spell " + getName()); }
        return b;
    }

    public boolean isCastSuccessful(Wizard wizard) {
        return isCastSuccessful(wizard, null);
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

    public Display getDisplay() {
        return display;
    }

    public InputParser getInputParser() {
        return inputParser;
    }

}
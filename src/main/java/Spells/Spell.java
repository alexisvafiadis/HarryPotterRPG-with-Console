package Spells;

import Characters.Character;
import Characters.EnemyWizard;
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
    protected Character wizard;// character and not wizard because there is Wizard and EnemyWizard
    protected String specificCastMessage;
    final private String CAST_MESSAGE_SUCCESS = "successfully cast";
    final private String CAST_MESSAGE_FAIL = "failed to cast";

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

    public boolean isCastSuccessful(Character target) {
        double probability;
        if (wizard instanceof Wizard) {
            probability = getMasteryScore() * ((Wizard) wizard).getAccuracy();
        }
        else { // <=> wizard instanceof EnemyWizard
            probability = getDefaultMasteryScore() * ((EnemyWizard) wizard).getDefaultMasteryScoreMultiplier();
        }
        if (target != null) { probability = probability * target.getVulnerabilityToMagic(); }
        boolean b = (Math.random() < probability) ;
        if (!b) {
            if (wizard instanceof Wizard) {
                display.announceFail(getCastMessageStart() + " " + CAST_MESSAGE_FAIL);
            }
            else {
                display.displayInfo(getCastMessageStart() + " " + CAST_MESSAGE_FAIL);
            }
        }
        return b;
    }

    public void displayCastMessage(String specificCastMessage) {
        String castMessage = getCastMessageStart() + " " + CAST_MESSAGE_SUCCESS + " and " + specificCastMessage;
        if (wizard instanceof Wizard) {
            display.announceSuccess(castMessage);
        }
        else {
            display.displayInfo(castMessage);
        }
    }

    public boolean isCastSuccessful() {
        return isCastSuccessful(null);
    }

    //Call that function when the player uses the spell so that they can get better at using it
    public void use() { nbOfUses += 1; };

    public String getCastMessageStart() {
        String castMessageStart;
        if (wizard instanceof Wizard) {
            castMessageStart = "You have";
        }
        else {
            castMessageStart = getName() + "has";
        }
        return castMessageStart;
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

    public Character getWizard() {
        return wizard;
    }

    public int getNbOfUses() {
        return nbOfUses;
    }

    public double getDefaultMasteryScore() {
        return defaultMasteryScore;
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

    public void setSpecificCastMessage(String specificCastMessage) {
        this.specificCastMessage = specificCastMessage;
    }

    public double calculateDamage(double damage) {
        if (wizard instanceof Wizard) {
            return ((Wizard) wizard).amplifySpellDamage(damage);
        }
        else {
            return ((EnemyWizard) wizard).amplifySpellDamage(damage);
        }
    }
}
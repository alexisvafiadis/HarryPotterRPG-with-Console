package Potions;

import Characters.Wizard;
import Console.Display;
import Console.InputParser;
import Game.Game;

public class Potion {
    Game game;
    Display display;
    InputParser inputParser;
    Wizard owner;
    PotionType potionType;

    public Potion(Game game, Wizard wizard, PotionType potionType) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        owner = wizard;
        this.potionType = potionType;
    }

    public void use() {
        String effectAnnouncement = "";
        double potionValue = potionType.getEffectValue() * owner.getHouse().getPOTION_EFFICIENCY_MULTIPLIER();
        String durationString = "x for " + potionType.getDuration() + " rounds";
        switch (potionType.getEffect()) {
            case HEAL:
                owner.heal(potionValue);
                effectAnnouncement = "been healed by " + potionValue + " HP";
                break;
            case STRENGTH:
                owner.boostStrength(potionType.getDuration(), potionValue);
                effectAnnouncement = "become stronger by " + potionValue + durationString;
                break;
            case RESISTANCE:
                owner.boostResistance(potionType.getDuration(), potionValue);
                effectAnnouncement = "become more resistant by " + potionValue + durationString;
            default:
                display.displayInfo("The potion effect has not been found");
        }
        display.announceSuccess("You have consumed your " + potionType.toString() + " and have " + effectAnnouncement);
    }

    public PotionType getPotionType() {
        return potionType;
    }
}

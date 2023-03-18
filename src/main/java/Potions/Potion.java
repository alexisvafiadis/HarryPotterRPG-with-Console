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
        switch (potionType.getEffect()) {
            case HEAL:
                owner.heal(potionType.getEffectValue());
                effectAnnouncement = "been healed by " + potionType.getEffectValue() + " HP";
                break;
            case STRENGTH:
                owner.strengthen(potionType.getDuration(), potionType.getEffectValue());
                effectAnnouncement = "become stronger by " + potionType.getEffectValue() + "x for " + potionType.getDuration() + " seconds";
                break;
            default:
                display.displayInfo("The potion effect has not been found");
        }
        display.announceSuccess("You have consumed your " + potionType.toString() + " and have " + effectAnnouncement);
    }

    public PotionType getPotionType() {
        return potionType;
    }
}

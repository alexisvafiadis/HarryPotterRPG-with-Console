package Magic;

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
        double potionValue = potionType.getEffectValue() * owner.getHouse().getPOTION_EFFICIENCY_MULTIPLIER();
        String message = "You have consumed your " + potionType.toString();// + " and are " + potionType.getEffect().getStartMessage();
        switch (potionType.getEffect()) {
            case HEAL:
                owner.heal(potionValue);
                message = message + " " + potionValue + " HP";
                break;
            case STRENGTH:
                owner.giveEffect(potionType.getEffect(), new ActiveEffect(potionType.getDuration(), potionValue));
                break;
            case RESISTANCE:
                owner.giveEffect(potionType.getEffect(), new ActiveEffect(potionType.getDuration(), potionValue));
            default:
                display.displayInfo("The potion effect has not been found");
        }
        display.announceSuccess(message);
    }

    public PotionType getPotionType() {
        return potionType;
    }
}

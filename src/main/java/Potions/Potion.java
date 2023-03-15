package Potions;

import Characters.Wizard;
import Game.Game;

public class Potion {
    double effectValue;
    Wizard owner;
    String name;
    Effect effect;
    Game game;

    public Potion(Game game, Wizard wizard, String name, Effect effect, double effectValue, double duration) {
        this.game = game;
        owner = wizard;
        this.name = name;
        this.effect = effect;
        this.effectValue = effectValue;
    }

    public void use() {
        switch (effect) {
            case HEAL:
                owner.heal(effectValue);
                String effectAnnouncement = "been healed by " + effectValue + " HP";
                break;
        }
        game.announceSuccess("You have consumed your " + name + "potion and have ");
    }

    public String getName() {
        return name;
    }

    public Effect getEffect() {
        return effect;
    }
}

package Potions;

import Game.Game;

public enum PotionType {
    STRENGHTENING_SOLUTION(Effect.STRENGTH, 2, 10, 1,"This potion is used to strengthen and fortify objects, and can be used to temporarily increase the player's physical strength."),
    WIGGENWELD_POTION(Effect.HEAL, 50, 0, 1," This potion is a healing potion that can be used to heal minor injuries, such as cuts and bruises. It is also known to revive a person from unconsciousness.");

    private Effect effect;
    private int duration;
    private double effectValue;
    private String description;
    private double weight;

    PotionType(Effect effect, double effectValue, int duration, double weight, String description) {
        this.description = description;
        this.effect = effect;
        this.effectValue = effectValue;
        this.duration = duration;
        this.weight = weight;
    }

    @Override
    public String toString() { return name().toLowerCase().replace("_", " "); }

    public Effect getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public double getEffectValue() {
        return effectValue;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }
}

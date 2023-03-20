package Potions;

public enum EffectType {
    CONFUSION("now confused", "no longer confused"),
    LAUGH("now laughing", "no longer laughing"),
    HIDE("now hiding", "no longer hiding"),
    STRENGTH("becoming stronger", "no longer affected by an effect of strength"),
    RESISTANCE("becoming more resistant", "no longer affected by an effect of resistance"),
    HEAL("healed by",""),;
    /*
    INVISIBILITY(""),
    SPEED("");
     */

    private String startMessage;
    private String endMessage;

    EffectType(String startMessage, String endMessage) {
        this.startMessage = startMessage;
        this.endMessage = endMessage;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public String getStartMessage() {
        return startMessage;
    }
}

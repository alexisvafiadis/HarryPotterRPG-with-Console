package Potions;

public enum EffectType {
    CONFUSION("now confused", "no longer confused"),
    LAUGH("now laughing", "no longer laughing"),
    STRENGTH("becoming stronger", "no longer affected by an effect of strength"),
    RESISTANCE("becoming more resistant", "no longer affected by an effect of resistance"),
    HEAL("healed by",""),
    SLUG_VOMITING("now feeling really nauseous","no longer nauseous"),
    DANCING("now feeling their legs spasm widly out of control","in full control of their legs again"),
    //Concrete effects
    HIDE("now hiding", "no longer hiding"),
    DISARM("now disarmed", "has managed to take their weapon back");
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

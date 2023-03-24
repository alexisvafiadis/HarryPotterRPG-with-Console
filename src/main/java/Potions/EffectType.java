package Potions;

public enum EffectType {
    CONFUSION("now confused", "already confused","no longer confused"),
    LAUGH("now laughing", "already laughing","no longer laughing"),
    STRENGTH("becoming stronger", "already affected by strength","no longer affected by an effect of strength"),
    RESISTANCE("becoming more resistant", "already affected by resistance","no longer affected by an effect of resistance"),
    HEAL("healed by","",""),
    SLUG_VOMITING("now feeling really nauseous","already nauseous","no longer nauseous"),
    DANCING("now feeling their legs spasm widly out of control","already dancing","in full control of their legs again"),
    //Concrete effects
    HIDE("now hiding", "","no longer hiding"),
    DISARM("now disarmed","already disarmed","has managed to take their weapon back");
    /*
    INVISIBILITY(""),
    SPEED("");
     */

    private String startMessage;
    private String alreadyAffectedMessage;
    private String endMessage;

    EffectType(String startMessage, String alreadyAffectedMessage, String endMessage) {
        this.startMessage = startMessage;
        this.alreadyAffectedMessage = alreadyAffectedMessage;
        this.endMessage = endMessage;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public String getAlreadyAffectedMessage() {
        return alreadyAffectedMessage;
    }

    public String getStartMessage() {
        return startMessage;
    }
}

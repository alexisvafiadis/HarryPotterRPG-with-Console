package Magic;

public enum EffectType {
    CONFUSION("now confused", "already confused","no longer confused"),
    LAUGH("now laughing", "already laughing","no longer laughing"),
    STRENGTH("becoming stronger", "already affected by strength","no longer affected by an effect of strength"),
    RESISTANCE("becoming more resistant", "already affected by resistance","no longer affected by an effect of resistance"),
    HEAL("healed by","",""),
    SLUG_VOMITING("now feeling really nauseous","already nauseous","no longer nauseous"),
    DANCING("now feeling their legs spasm widly out of control","already dancing","in full control of their legs again"),
    EXCRUCIATING_PAIN("now writhing in excruciating pain on the ground","already being tortured","no longer in pain"),
    BURN("now burning","already burning","no longer burning"),
    STUN("now stunned", "already stunned","no longer stunned"),
    //Effects that would need to be accompanied by visuals
    HIDE("now hiding", "","no longer hiding"),
    DISARM("now disarmed","already disarmed","armed again"),
    SHIELD("now protected by a shield","already protected by a shield","no longer protected by a shield"),
    INVISIBILITY("now invisible","already invisible","no longer invisible");

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

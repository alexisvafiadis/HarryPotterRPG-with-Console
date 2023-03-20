package Items;

import Characters.Wizard;

public class Wand {
    private Core core;
    private Wood wood;
    private int size;
    private Wizard owner;
    private int nbOfUses;

    public Wand(Core core, Wood wood, int size, Wizard owner) {
        this.core = core;
        this.size = size;
        this.wood = wood;
        this.owner = owner;
        nbOfUses = 0;
    }

    public Core getCore() {
        return core;
    }

    public Wood getWood() {
        return wood;
    }

    public int getSize() {
        return size;
    }

    public Wizard getOwner() {
        return owner;
    }

    public int getNbOfUses() {
        return nbOfUses;
    }
}

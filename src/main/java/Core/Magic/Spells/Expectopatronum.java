package Core.Magic.Spells;

import Core.Characters.Wizard;
import Core.Game.Game;
import Core.Magic.Spell;

public class Expectopatronum extends Spell {

    public Expectopatronum(Game game, Wizard wizard) {
        super(game, wizard, "Expecto Patronum", 5, 1, 0.05, 0);
    }

    public boolean cast() {
        use();
        boolean isCastSuccessful = isCastSuccessful();
        if (isCastSuccessful) {
            displayCastMessage("summoned a glowing, silvery animal called Patronus to repel the Dementors.");
        }
        return isCastSuccessful;
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForYes("When casting Expecto Patronum, focus on a positive memory that makes you feel happy and confident.\n" +
                "Point your wand in front of you and make a sweeping motion upward as you say the incantation.\n" +
                "This spell can be useful for confusing opponents and creating an opportunity to attack or escape.\n" +
                "A silvery-white mist will appear, and as you continue to focus on your happy memory, the mist will take the shape of an animal, your Patronus." +
                "Understood?");
    }
}

package Core.Magic.Spells;

import Core.Characters.Wizard;
import Core.Items.Item;
import Core.Game.Game;
import Core.Items.Weapon;
import Core.Magic.Spell;

public class Accio extends Spell {

    public Accio(Game game, Wizard wizard) {
        super(game, wizard, "Accio", 5, 1, 0.7, 0.34);
    }

    public void cast(Weapon weapon) {
        use();
        if (isCastSuccessful()) {
            wizard.setWeapon(weapon);
            displayCastMessage("summoned a " + weapon.toString());
        }
    }

    public boolean cast(Item item) {
        use();
        boolean castSuccessful = isCastSuccessful();
        if (castSuccessful) {
            if (Math.random() < 1 / (Math.pow(wizard.getMap().calculateDistance(wizard,item),4))) {
                castSuccessful = true;
                displayCastMessage("summoned a " + item.getItemType().toString());
            }
            else {
                castSuccessful = false;
                display.announceFail("Accio failed. You might be too far.");
            }
        }
        return castSuccessful;
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForYes("First, have your wand at the ready. Then, focus your attention on the object you want to summon. Visualize the object in your mind and concentrate on it.\n" +
                "Now, hold out your wand and say \"Accio\" followed by the name of the object you want to summon. For example, \"Accio broomstick!\"\n" +
                "If the spell is successful, the object should fly towards you. Be ready to catch it, or move out of the way if it's a larger object.\n" +
                "Practice your casting and timing to improve your success rate with the spell. Remember, the Accio spell only works on objects within your line of sight.\n" +
                "Understood?");
    }
}

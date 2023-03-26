package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.SimpleSpell;

public class AvadaKedavra extends SimpleSpell {
    private final double DEFAULT_DAMAGE = 1000;

    public AvadaKedavra(Game game, Character wizard) {
        super(game, wizard, "Avada Kedavra", 5, 1, 0.2, 0.22);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DEFAULT_DAMAGE));
            displayCastMessage("emitted a bright green flash of light, striking " + target.getName() + " and causing them to fall to the ground, lifeless.");
        }
    }

    @Override
    public void displayInstructions() {

    }
}

package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.SimpleSpell;

public class AvadaKedavra extends SimpleSpell {
    private final double DEFAULT_DAMAGE = 1000;

    public AvadaKedavra(Game game, Character wizard) {
        super(game, wizard, "Avada Kedavra", 5, 1, 0.15, 0.28);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DEFAULT_DAMAGE));
            displayCastMessage("a bright green flash of light is emitted, striking " + target.getName() + " and causing them to fall to the ground, lifeless.");
        }
    }
}

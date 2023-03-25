package Magic.Spells;

import Characters.Character;
import Game.Game;
import Magic.SimpleSpell;

public class Sectumsempra extends SimpleSpell {
    private final double DEFAULT_DAMAGE = 40;

    public Sectumsempra(Game game, Character wizard) {
        super(game, wizard, "Sectumsempra", 5, 1, 0.32, 0.58);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DEFAULT_DAMAGE));
            displayCastMessage("lacerated " + target.getName() + ", causing severe haemorrhaging");
        }
    }
}

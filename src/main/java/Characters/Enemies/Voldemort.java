package Characters.Enemies;

import Characters.EnemyWizard;
import Game.Game;
import Items.Weapon;
import Spells.Expelliarmus;
import Spells.Sectumsempra;

public class Voldemort extends EnemyWizard {
    public Voldemort(Game game) {
        super(game, 600, 5, 0.65, 'V', 1, 2.5, 2);
        addSpell(new Sectumsempra(game, this), 1);
        addSpell(new Expelliarmus(game, this), 1);
    }

    @Override
    public String getName() {
        return "Voldemort";
    }

}

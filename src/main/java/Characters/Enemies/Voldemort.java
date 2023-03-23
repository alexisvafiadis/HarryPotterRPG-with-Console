package Characters.Enemies;

import Characters.EnemyWizard;
import Game.Game;
import Items.Weapon;

public class Voldemort extends EnemyWizard {
    public Voldemort(Game game) {
        super(game, 600, 5, 0.65, 'V', 1, 2.5, 2);
    }

    @Override
    public void act() {

    }

    @Override
    public String getName() {
        return "Voldemort";
    }

    @Override
    public void attackedByExpelliarmus() {

    }
}

package Characters.Enemies;

import Characters.EnemyWizard;
import Game.Game;
import Magic.Spells.*;

public class Voldemort extends EnemyWizard {
    public Voldemort(Game game) {
        super(game, 220, 5, 0.65, 'V', 1, 2.3, 5.2);
        addSpell(new AvadaKedavra(game, this), 5);
        addSpell(new Fiendfyre(game, this), 2);
        addSpell(new Crucio(game, this), 2);
        addSpell(new Expelliarmus(game, this), 1);
        setCustomBattleStartMessage("What are you doing here? You really are foolish. I'll put an end to this once and for all.");
    }

    @Override
    public String getName() {
        return "Voldemort";
    }

}

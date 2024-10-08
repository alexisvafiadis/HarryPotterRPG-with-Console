package Core.Characters.Enemies;

import Core.Characters.EnemyWizard;
import Core.Game.Game;
import Core.Magic.Spells.AvadaKedavra;
import Core.Magic.Spells.Crucio;
import Core.Magic.Spells.Expelliarmus;
import Core.Magic.Spells.Fiendfyre;

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

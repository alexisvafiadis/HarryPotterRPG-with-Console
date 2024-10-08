package Core.Characters.Enemies;

import Core.Characters.EnemyWizard;
import Core.Game.Game;
import Core.Magic.Spells.*;

public class HermioneGranger extends EnemyWizard {
    public HermioneGranger(Game game) {
        super(game, 145, 5, 0.45, 'V', 1, 1.6, 6);
        addSpell(new Sectumsempra(game, this), 4);
        addSpell(new Stupefy(game, this), 1);
        addSpell(new Expelliarmus(game, this), 1);
        addSpell(new Protego(game, this), 1);
        addSpell(new Confundus(game, this), 1);
        setCustomBattleStartMessage("I hope you're ready to face the consequences of your actions. I will not miss a single spell.");
    }

    @Override
    public String getName() {
        return "Hermione Granger";
    }

}

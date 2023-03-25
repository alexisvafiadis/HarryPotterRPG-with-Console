package Characters.Enemies;

import Characters.EnemyWizard;
import Game.Game;
import Magic.Spells.*;

public class BellatrixLestrange extends EnemyWizard {
    public BellatrixLestrange(Game game) {
        super(game, 200, 5, 0.7, 'V', 1, 2, 1.8);
        addSpell(new AvadaKedavra(game, this), 5);
        addSpell(new Sectumsempra(game, this), 4);
        addSpell(new Protego(game, this), 1);
        addSpell(new Crucio(game, this), 2);
        setCustomBattleStartMessage("I've been waiting for this moment for a long time");
    }

    @Override
    public String getName() {
        return "Bellatrix Lestrange";
    }

}

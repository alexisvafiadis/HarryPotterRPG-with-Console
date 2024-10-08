package Core.Characters.Enemies;

import Core.Characters.EnemyWizard;
import Core.Game.Game;
import Core.Magic.Spells.AvadaKedavra;
import Core.Magic.Spells.Crucio;
import Core.Magic.Spells.Protego;
import Core.Magic.Spells.Sectumsempra;

public class BellatrixLestrange extends EnemyWizard {
    public BellatrixLestrange(Game game) {
        super(game, 200, 5, 0.7, 'V', 1, 2, 4.4);
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

package Characters.Enemies;

import Characters.EnemyWizard;
import Game.Game;
import Magic.Spells.*;

public class HarryPotter extends EnemyWizard {
    public HarryPotter(Game game) {
        super(game, 180, 5, 0.53, 'V', 1, 2.1, 2.1);
        addSpell(new Sectumsempra(game, this), 4);
        addSpell(new Stupefy(game, this), 1);
        addSpell(new Expelliarmus(game, this), 2);
        addSpell(new Protego(game, this), 2);
        addSpell(new Confundus(game, this), 1);
        setCustomBattleStartMessage("I'll never back down, no matter the odds. I will fight for what's right and stop you.");
    }

    @Override
    public String getName() {
        return "Harry Potter";
    }

}

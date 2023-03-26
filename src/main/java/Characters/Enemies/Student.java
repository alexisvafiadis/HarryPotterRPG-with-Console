package Characters.Enemies;

import Characters.EnemyWizard;
import Game.Game;
import Magic.Spells.*;

public class Student extends EnemyWizard {
    private String name;

    public Student(Game game, String name, double maxHP, double vulnerabilityToMagic, double spellDamageMultiplier, double defaultMasteryScoreMultiplier, String customBattleStartMessage) {
        super(game, maxHP, 5, vulnerabilityToMagic, 'S', 1, spellDamageMultiplier, defaultMasteryScoreMultiplier);
        this.name = name;
        setCustomBattleStartMessage(customBattleStartMessage);
        addSpell(new Sectumsempra(game, this), 5);
        addSpell(new Expelliarmus(game, this), 1);
        addSpell(new Confundus(game, this), 1);
        addSpell(new Stupefy(game, this), 1);
        addSpell(new Protego(game, this), 1);
    }

    @Override
    public String getName() {
        return name;
    }

}

package Core.Levels.Essentials;

import Core.Characters.AbstractEnemy;
import Core.Characters.Enemies.DoloresUmbridge;
import Core.Characters.Wizard;
import Core.Game.Game;
import Core.Levels.Level;

public class DoloresUmbridgeBattle extends Battle {
    final int NB_OF_ROUNDS_BEFORE_WIN = 15;

    public DoloresUmbridgeBattle(Game game, Level level, Wizard player, AbstractEnemy enemy) {
        super(game, level, player, enemy);
    }

    @Override
    public boolean getBattleLossCondition() {
        return (super.getBattleLossCondition() || !enemy.isAlive() || !(((DoloresUmbridge) enemy).isDistracted()));
    }

    @Override
    public boolean getBattleWinCondition() {
        return (roundNumber > NB_OF_ROUNDS_BEFORE_WIN);
    }

    @Override
    public void displayBattleStartMessage() {
        display.displayInfo("Choose the right spells!");
    }

    @Override
    public void displayBattleWinMessage() {
    }
}

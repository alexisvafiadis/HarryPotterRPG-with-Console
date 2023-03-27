package Levels.Essentials;

import Characters.AbstractEnemy;
import Characters.Enemies.DoloresUmbridge;
import Characters.Wizard;
import Game.Game;
import Levels.Level;

public class DoloresUmbridgeBattle extends Battle {
    final int NB_OF_ROUNDS_BEFORE_WIN = 15;

    public DoloresUmbridgeBattle(Game game, Level level, Wizard player, AbstractEnemy enemy) {
        super(game, level, player, enemy);
    }

    @Override
    public boolean getFightContinueCondition() {
        return (player.isAlive() && enemy.isAlive() && ((DoloresUmbridge) enemy).isDistracted() && roundNumber < NB_OF_ROUNDS_BEFORE_WIN);
    }

    @Override
    public void displayFightStartMessage() {
        display.displayInfo("Choose the right spells!");
    }

    @Override
    public void displayFightWinMessage() {
        display.announceSuccess("Well done, you have distracted Dolores Umbridge for enough time!");
    }
}

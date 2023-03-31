package Levels.Essentials;

import Characters.AbstractEnemy;
import Characters.Characteristics.House;
import Characters.Enemies.DeathEater;
import Characters.Enemies.Troll;
import Characters.Wizard;
import Game.Game;
import Levels.Level1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    Wizard wizard;
    AbstractEnemy enemy;
    Game game;
    Battle battle;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setHouse(House.GRYFFINDOR);
        wizard.spawn();
        enemy = new Troll(game);
        enemy.spawn();
        Level1 level = new Level1(game);
        battle = new Battle(game, level, wizard, enemy);
    }

    @Test
    void checkBattleContinueConditions() {
        game.getDisplay().displayInfo(String.valueOf(battle.getBattleContinueCondition()));
        assertTrue(battle.getBattleContinueCondition(), "The battle cannot start");
        wizard.damage(wizard.getMaxHP() + 100);
        assertFalse(battle.getBattleContinueCondition(), "The battle is not lost after the player is dead");
        assertTrue(battle.getBattleLossCondition(), "The battle is not lost after the player is dead");
        assertFalse(battle.getBattleWinCondition(), "The battle is not lost after the player is dead");
    }

    @Test
    void enemyAttack() {

    }

    @Test
    void finishRound() {
    }

    @Test
    void generateItemType() {
    }

    @Test
    void generatePotionType() {
    }
}
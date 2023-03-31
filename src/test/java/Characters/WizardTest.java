package Characters;

import Characters.Characteristics.House;
import Game.Game;
import Magic.Potion;
import Magic.PotionType;
import Magic.Spells.WingardiumLeviosa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {
    Wizard wizard;
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setHouse(House.GRYFFINDOR);
        wizard.spawn();
    }

    @Test
    void damage() {
        wizard.damage(40);
        assertTrue(wizard.isAlive());
        wizard.damage(200);
        assertTrue(!wizard.isAlive());
    }

    @Test
    void amplifyDamage() {
        double damage = wizard.amplifyDamage(200.0);
        assertTrue(damage >= 200);
    }

    @Test
    void amplifySpellDamage() {
        double damage = wizard.amplifySpellDamage(200.0);
        assertTrue(damage >= 200);
    }

    @Test
    void defendDamage() {
        double damage = wizard.defendDamage(200.0);
        assertTrue(damage <= 200);
    }

    @Test
    void learnSpell() {
        wizard.learnSpell(new WingardiumLeviosa(game, wizard));
    }

    @Test
    void knowsSpell() {
        wizard.learnSpell(new WingardiumLeviosa(game, wizard));
        assertTrue(wizard.knowsSpell("Wingardium Leviosa"));
    }

    @Test
    void addPotion() {
        for (PotionType potionType : PotionType.values()) {
            if ((potionType.equals(PotionType.WIGGENWELD_POTION) ||potionType.equals(PotionType.ESSENCE_OF_DITTANY))) {
                continue;
            }
            Potion potion = new Potion(game, wizard, potionType);
            wizard.addPotion(potion);
            wizard.consumePotion(potion);
            assertTrue(wizard.hasEffect(potionType.getEffect()));
        }
    }

    @Test
    void doEffectsGoAway() {
        PotionType potionType = PotionType.ENDURUS_POTION;
        Potion potion = new Potion(game, wizard, PotionType.ENDURUS_POTION);
        wizard.addPotion(potion);
        wizard.consumePotion(potion);
        for (int i = 0 ; i <= potion.getPotionType().getDuration() ; i++) {
            wizard.finishRound();
        }
        assertTrue(!wizard.hasEffect(potionType.getEffect()));
    }

}
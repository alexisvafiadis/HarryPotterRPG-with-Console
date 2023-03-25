package Levels;

import Characters.Characteristics.House;
import Characters.Enemies.*;
import Items.Weapon;
import Game.Game;
import Levels.Essentials.Battle;
import Magic.Spells.Accio;

import java.util.HashMap;

public class Level7 extends Level{

    public Level7(Game game) {
        super(game, "The Half-blood Prince","Astronomy Tower", 6, true);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        if (player.isAgainstDeathEaters()) {
            Voldemort voldemort = new Voldemort(game);
            voldemort.spawn();
            new Battle(game,this,player,voldemort);
            BellatrixLestrange bellatrix = new BellatrixLestrange(game);
            bellatrix.spawn();
            new Battle(game,this,player,bellatrix);
        }
        else {
            HarryPotter harry = new HarryPotter(game);
            harry.spawn();
            new Battle(game, this, player, harry);
            RonWeasley ron = new RonWeasley(game);
            ron.spawn();
            new Battle(game, this, player, ron);
            HermioneGranger hermione = new HermioneGranger(game);
            hermione.spawn();
            new Battle(game, this, player, hermione);
        }
        finish();
    }

    @Override
    public void conclude() {

    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("For this level, you have to defeat the Basilisk. It is a very venomous snake that kills anyone that makes eye contact with it.");
        wishGoodLuck();
    }

}

package Levels;

import Characters.Characteristics.House;
import Characters.Enemies.DeathEater;
import Characters.Enemies.Student;
import Items.Weapon;
import Game.Game;
import Levels.Essentials.Battle;
import Magic.Spells.Accio;
import Magic.Spells.Sectumsempra;
import Magic.Spells.Stupefy;
import Magic.Spells.WingardiumLeviosa;

import java.util.HashMap;

public class Level6 extends Level{

    public Level6(Game game) {
        super(game, "The Half-blood Prince","Astronomy Tower", 6, true);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        if (player.isAgainstDeathEaters())  {
            DeathEater alecto = new DeathEater(game, "Alecto Carrow", 110, 1, 1.6, 4.2, "I'll make sure you regret ever crossing the Dark Lord.");
            alecto.spawn();
            new Battle(game, this, player, alecto);
            DeathEater malfoy = new DeathEater(game, "Lucius Malfoy", 125, 0.9, 1.85, 3.3, "My loyalty lies with the Dark Lord.");
            malfoy.spawn();
            new Battle(game, this, player, malfoy);
            DeathEater yaxley = new DeathEater(game, "Corban Yaxley", 190, 0.7, 1, 4.7, "I serve the Dark Lord and nothing else. You will not break through my defense.");
            yaxley.spawn();
            new Battle(game, this, player, yaxley);
            DeathEater dolohov = new DeathEater(game, "Antonin Dolohov", 130, 0.85, 2.15, 3.8, "I'll take pleasure in taking you down!");
            dolohov.spawn();
            new Battle(game, this, player, dolohov);
        }
        else {
            Student cho = new Student(game, "Cho Chang", 90, 1, 1, 4.4, "I won't let you hurt my friends or my school.");
            cho.spawn();
            new Battle(game,this,player,cho);
            Student neville = new Student(game, "Neville Longbottom", 140,1.05,1.05,2.9, "I'm not the same boy you used to know. I've learned a few things since then.");
            neville.spawn();
            new Battle(game,this,player,neville);
            Student ginny = new Student(game, "Ginny Weasley", 100, 0.85, 1.2, 3.4, "I may be small, but I can pack a punch.");
            ginny.spawn();
            new Battle(game,this,player,ginny);
            Student cedric = new Student(game, "Cedric Diggory", 155, 0.8, 1.45, 4, "Let's make this a fair fight, shall we?");
            cedric.spawn();
            new Battle(game,this,player,cedric);
        }
        finish();
    }

    @Override
    public void conclude() {
        if (player.isAgainstDeathEaters()) {
            display.congratulate("Well done, by winning those intense battles, you have helped Hogwarts defeat the Death Eaters and ensure the safety of Hogwarts.");
            display.displayInfo("You have proved yourself of worthy defender of Hogwarts.");
            display.displayInfo("However, with Voldemort still alive somewhere, the hardest task still has to be done. You should prepare yourself.");
        }
        else {
            display.displayInfo("As you stand among the fallen bodies of your former classmates, you feel the rush of power and victory, at the expense of your school, friends and principles.");
            display.congratulate("You have proven your worth to the Death Eaters and solidified your place among them by helping them win an important battle.");
            display.displayInfo("However, with Harry Potter, Hermione Granger and Ron Weasley still alive somewhere in Hogwarts, an extremely difficult fight is upcoming.");
        }
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("In this level, Hogwarts is under attack by the Death Eaters, a group of dark wizards and witches who are loyal to the evil Lord Voldemort.");
        display.displayInfo("As a student of Hogwarts, your duty is to defend your school and your fellow students from the impending attack.");
        boolean againstDeathEaters = true;
        if (player.getHouse().equals(House.SLYTHERIN)) {
            display.displayInfo("As you are a Slytherin, you have the option to join the ranks of the Death Eaters and fight against your fellow students and former allies.");
            display.displayInfo("But remember, every decision you make in this level will have consequences. Choose your side wisely, as your actions will impact the rest of the game");
            HashMap<Integer, String> validInputs = new HashMap<>();
            validInputs.put(0,"No");
            validInputs.put(1,"Yes");
            int choice = inputParser.getNumberInput("So, do you wish to side with the Death Eaters?", validInputs,"for");
            if (choice == 1) { againstDeathEaters = false; }
        }
        else {
            display.displayInfo("Therefore, you will fight against the Death Eaters.");
        }
        String enemies;
        if (againstDeathEaters) {  enemies = "death eaters"; }
        else { enemies = "students"; }
        display.displayInfo("As you navigate through the level, you will encounter " + enemies + " with different abilities, strengths and weaknesses");
        display.displayInfo("Each encounter will be more difficult than the last. You will need to be strategic and use your spells wisely to ensure your survival.");
        display.displayInfo("This will be a challenging battle. The Sectumsempra spell will be essential to attack " + enemies + " head-on");
        (new Sectumsempra(game, player)).teach(player);
        player.setAgainstDeathEaters(againstDeathEaters);
        wishGoodLuck();
    }


}

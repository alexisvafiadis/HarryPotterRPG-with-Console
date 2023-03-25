package Levels;

import Characters.Enemies.Voldemort;
import Game.Game;
import Items.Item;
import Items.ItemType;
import Levels.Essentials.Battle;
import Levels.Essentials.LevelMap;
import Magic.Spells.*;

import java.util.HashMap;

public class Level4 extends Level{
    final int MIN_POTTER_VOLDEMORT_DISTANCE = 4;
    boolean hasPortkey;
    Item portkey;
    LevelMap map;
    Voldemort voldemort;

    public Level4(Game game) {
        super(game, "The Goblet of Fire","Little Hangleton graveyard", 4, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        map = new LevelMap(10,10);
        player.spawn(5,0,map);
        portkey = new Item(ItemType.PORTKEY, 7,9, map, 'P');
        hasPortkey = false;
        super.start();
        voldemort = new Voldemort(game);
        voldemort.spawn(3,5,map);
        while (!seenByVoldemort() && !hasPortkey) {
            askForAction();
        }
        if (seenByVoldemort()) {
            new Battle(game, this, player, voldemort);
        }
        else {
            inputParser.waitForYes("Visualize your room in Hogwarts in your head, then touch the Portkey.");
            display.displayInfo("The power of the Portkey teleports you back to Hogwarts...");
        }
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("Well done, you have gotten away!");
        teachFunnySpells();
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("Voldemort and Peter Pettigrew are nearby. You should probably get away.");
        display.displayInfo("You have to get close to the Portkey to attract it to you using Accio then use the Portkey");
        wishGoodLuck();
    }

    public void teachFunnySpells() {
        display.displayInfo("To help you recover, let me teach you some funny spells. Let's tart with Rictumsempra.");
        inputParser.waitForYes("First, make sure you have your wand at the ready and choose your target carefully.\n" +
                "The Rictumsempra spell is a jinx that causes your target to experience a tickling sensation, so it's a harmless spell that wouldn't be useful in a dangerous situation.\n" +
                "Point your wand at your target and clearly enunciate Rictumsempra while flicking your wand in a zig-zag motion.\n" +
                "If successful, your target should start to experience an uncontrollable tickling sensation, causing them to double over with laughter.\n");
        player.learnSpell(new Rictumsempra(game, player));

        display.displayInfo("There's also Slugulus Erecto, a spell that causes the target to vomit slugs for a short period");
        inputParser.waitForYes("To begin casting the Slug-vomiting Charm, visualize your target being overcome with the uncontrollable urge to vomit slugs.\n" +
                "Firmly grasp your wand and prepare to perform a smooth, circular motion while keeping your focus on the target.\n" +
                "As you execute the circular motion with your wand, confidently and clearly enunciate the incantation Slugulus Eructo.\n" +
                "The effectiveness of the Slug-vomiting Charm relies on the caster's ability to concentrate on their target and deliver the incantation with precision and confidence.");
        player.learnSpell(new SlugulusErecto(game, player));

        display.displayInfo("You can learn Tarantallegra as well.");
        inputParser.waitForYes("To cast Tarantallegra, first focus your mind on the target and imagine their feet moving uncontrollably in a dance.\n" +
                "While concentrating on your target, hold your wand firmly and perform a swift, upward flicking motion.\n" +
                "As you flick your wand, confidently and clearly pronounce the incantation Tarantallegra.\n" +
                "Remember, the key to a successful Tarantallegra spell is maintaining a strong mental image of the target dancing and delivering the incantation with confidence.");
        player.learnSpell(new Tarantallegra(game, player));

    }

    public boolean seenByVoldemort() {
        return (map.calculateDistance(player, voldemort) < MIN_POTTER_VOLDEMORT_DISTANCE);
    }

    public void askForAction() {
        HashMap<Integer, String> actionInputs = new HashMap<>();
        actionInputs.put(1,"Move");
        actionInputs.put(2,"Try to use Accio on the Portkey");
        actionInputs.put(3,"Use Lumos");
        String actionChoice = inputParser.getNumberToStringInput("What do you want to do?", actionInputs,"to");
        switch(actionChoice) {
            case "Move":
                askForDirections();
                break;
            case "Try to use Accio on the Portkey":
                if ((((Accio) player.getKnownSpells().get("Accio")).cast(portkey))) {
                    hasPortkey = true;
                }
                break;
            case "Use Lumos":
                ((Lumos) player.getKnownSpells().get("Lumos")).cast(map);
                break;
        }
    }

}

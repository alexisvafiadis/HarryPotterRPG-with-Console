package Characters;

import Items.Item;
import Items.Weapon;
import Game.Game;

public class DoloresUmbridge extends Boss{
    int distractionLevel;

    public DoloresUmbridge(Game game) {
        setGame(game);
        setMaxHP(1);
        setPhysicalDamage(14);
        setAttackDelay(2);
        setVulnerabilityToMagic(1);
    }

    @Override
    public void spawn(double positionX, double positionY, double positionZ) {
        super.spawn(positionX, positionY, positionZ);
        distractionLevel = 2;
    }

    public void act() {
        if (canAttack(game.getPlayer())) {
            distractionLevel -= 1;
        }
        else {
            if (distractionLevel < 5) {
                distractionLevel += 1;
            }
        }
        roundTalk();
    }

    public String getName() {
        return "Dolores Umbridge";
    }

    public void roundTalk() {
        switch(distractionLevel) {
            case 0:
                speak("This noise is highly distracting. Students, please continue with your exams while I go check on the situation. Don't move.");
                break;
            case 1:
                speak("What even is happening outside? That sound is weird, I should probably check what is happening.");
                speak("But I don't know if I should leave you all without any surveillance...");
                break;
            case 2:
                speak("Do you hear this noise coming from outside? It's a bit distracting.");
                break;
            case 3:
                speak("If you don't stop using these ridiculous spells immediately, there will be consequences! Stop this!");
                break;
            case 4:
                speak("What even is this?? I'll have you all expelled if this continues! Who is causing all this trouble?");
                break;
            case 5:
                speak("Potter, I demand that you stop what you are doing right now! This is completely unacceptable.");
                break;
            case 6:
                speak("You've crossed a line, Potter. There will be no more leniency. I'll make you pay for this.");
                break;
        }
    }

    public boolean isDistracted() {
        return (distractionLevel != 0);
    }

}

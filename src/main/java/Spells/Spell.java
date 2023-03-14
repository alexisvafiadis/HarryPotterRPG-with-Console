package Spells;

public class Spell {
    String name;
    int range;
    float cooldown;
    int nbOfUses;

    public Spell(String name, int range, float cooldown) {
        this.name = name;
        this.range = range;
        this.cooldown = cooldown;
        nbOfUses = 0;
    }

    public double getMasteryScore() {
        double masteryScore = 1;
        if (nbOfUses < 10) {
            switch(nbOfUses) {
                case 0:
                    return 0.4;
                case 1:
                    return 0.6;
                case 2:
                    return 0.75;
                case 3:
                    return 0.825;
                case 4:
                    return 0.875;
                case 5:
                    return 0.915;
                case 6:
                    return 0.93;
                case 7:
                    return 0.945;
                case 8:
                    return 0.96;
                case 9:
                    return 0.985;
                default:
                    return 1;
            }
        }
        else {
            return 1;
        }
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }
}
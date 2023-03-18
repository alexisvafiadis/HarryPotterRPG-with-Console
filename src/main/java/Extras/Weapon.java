package Extras;

public enum Weapon {
    CLUB(6),
    SWORD_OF_GRYFFINDOR(20),;

    double attackDamage;

    Weapon(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public String toString() { return name().toLowerCase().replace("_", " "); }

    }

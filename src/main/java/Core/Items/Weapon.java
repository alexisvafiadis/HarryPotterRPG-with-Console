package Core.Items;

public enum Weapon {
    CLUB(6),
    SWORD_OF_GRYFFINDOR(26),
    BASILISK_FANG(25);

    double attackDamage;

    Weapon(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public String toString() { return name().toLowerCase().replace("_", " "); }

    }
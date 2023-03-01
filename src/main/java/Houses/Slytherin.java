package Houses;

public class Slytherin extends House{
    final double DAMAGE_MULTIPLIER = 1.2;
    @Override
    public String toString() {
        return "Slytherin";
    }

    public double getDamageMultiplier() {
        return DAMAGE_MULTIPLIER;
    }
}

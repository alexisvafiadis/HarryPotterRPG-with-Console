package Houses;

public class Hufflepuff extends House{
    final double SPELL_EFFICIENCY_MULTIPLIER = 1.25;

    @Override
    public String toString() {
        return "Hufflepuff";
    }

    public double getSpellEfficiencyMultiplier() {
        return SPELL_EFFICIENCY_MULTIPLIER;
    }
}

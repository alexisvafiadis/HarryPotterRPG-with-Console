package Houses;

public class Gryffindor extends House{
    final double DAMAGE_RESISTANCE = 0.8;

    @Override
    public String toString() {
        return "Gryffindor";
    }

    public double getDamageResistance() {
        return DAMAGE_RESISTANCE;
    }
}

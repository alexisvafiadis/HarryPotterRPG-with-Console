package Houses;

public class Ravenclaw extends House{
    final double ACCURACY_MULTIPLIER = 1.25;

    @Override
    public String toString() {
        return "Ravenclaw";
    }

    public double getAccuracyMultiplier() {
        return ACCURACY_MULTIPLIER;
    }
}

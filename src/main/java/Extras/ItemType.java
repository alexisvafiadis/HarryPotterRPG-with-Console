package Extras;

public enum ItemType {
    GLASS, BOTTLE, BOOK, STICK, BIG_ROCK, SMALL_ROCK, MEDIUM_ROCK, KNIFE, FORK, VASE, PAINTING, LAMP, TABLE, CHAIR;

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}

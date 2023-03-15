package Extras;

public enum ItemType {
    GLASS, BOTTLE, BOOK, SMALL_STICK, MEDIUM_STICK, BIG_STICK, NETTLE, BIG_ROCK, SMALL_ROCK, MEDIUM_ROCK, KNIFE, FORK, VASE, PAINTING, LAMP, TABLE, CHAIR;

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}

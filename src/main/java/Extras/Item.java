package Extras;

public class Item {
    double positionX;
    double positionY;
    double positionZ;
    ItemType itemType;
    double damageMultiplier;

    public Item(ItemType itemType, double positionX, double positionY, double positionZ) {
        this.itemType = itemType;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        //Decide damage multiplier of each item type.
        switch(itemType) {
            case BOOK:
                damageMultiplier = 0.4;
                break;
            case FORK:
                damageMultiplier = 2.25;
                break;
            case KNIFE:
                damageMultiplier = 2.75;
                break;
            case LAMP:
                damageMultiplier = 1.5;
                break;
            case VASE:
                damageMultiplier = 1.8;
                break;
            case CHAIR:
                damageMultiplier = 1.3;
                break;
            case GLASS:
                damageMultiplier = 1.6;
                break;
            case BOTTLE:
                damageMultiplier = 2;
                break;
            case TABLE:
                damageMultiplier = 1.5;
                break;
            case PAINTING:
                damageMultiplier = 0.75;
                break;
            case SMALL_STICK:
                damageMultiplier = 0.9;
                break;
            case MEDIUM_STICK:
                damageMultiplier = 1.3;
                break;
            case BIG_STICK:
                damageMultiplier = 1.7;
                break;
            case SMALL_ROCK:
                damageMultiplier = 1.7;
                break;
            case MEDIUM_ROCK:
                damageMultiplier = 2.4;
                break;
            case BIG_ROCK:
                damageMultiplier = 3.5;
                break;
            case NETTLE:
                damageMultiplier = 1.25;
                break;
        }
    }

    public ItemType getItemType() {
        return itemType;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void amplifyDamageMultiplier(double coefficient) {damageMultiplier = damageMultiplier * coefficient; }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(double positionZ) {
        this.positionZ = positionZ;
    }
}

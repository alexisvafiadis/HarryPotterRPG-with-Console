package Items;

public class Item {
    private double positionX;
    private double positionY;
    private double positionZ;
    private ItemType itemType;

    public Item(ItemType itemType, double positionX, double positionY, double positionZ) {
        this.itemType = itemType;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        //Decide damage multiplier of each item type.
    }

    public ItemType getItemType() {
        return itemType;
    }

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

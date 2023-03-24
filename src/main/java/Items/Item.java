package Items;

import Levels.Essentials.LevelMap;

public class Item {
    private LevelMap map;
    private char charTile;
    private double positionX;
    private double positionY;
    private ItemType itemType;

    public Item(ItemType itemType, int positionX, int positionY, LevelMap map, char charTile) {
        this.itemType = itemType;
        this.map = map;
        this.charTile = charTile;
        moveTo(positionX, positionY);
        //Decide damage multiplier of each item type.
    }

    public Item(ItemType itemType) {
        this.itemType = itemType;
    }

    public boolean moveTo(int positionX, int positionY) {
        boolean isPositionPossible = map.isPositionPossible(positionX, positionY);
        if (isPositionPossible) {
            this.positionX = positionX;
            this.positionY = positionY;
            map.setTile(positionX, positionY, charTile);
        }
        return isPositionPossible;
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
}

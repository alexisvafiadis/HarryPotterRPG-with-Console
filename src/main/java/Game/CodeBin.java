package Game;

import Items.ItemType;

import java.util.HashMap;

public class CodeBin {
    /*
    //just to put code that I replaced for a better one but could be useful later

    HashMap<ItemType, Integer> itemTypeWeights = new HashMap<>();

    public ItemType generateItemType() {
        ItemType itemType = null;
        double totalWeight = 0.0;
        for (int w : itemTypeWeights.values()) {
            totalWeight += w;
        }

        double r = Math.random() * totalWeight;
        for (ItemType it : itemTypeWeights.keySet()) {
            r -= itemTypeWeights.get(it);
            if (r <= 0.0) {
                itemType = it;
                break;
            }
        }
        return itemType;
    }

    public void initiateItemChances() {
        if (outdoors) {
            itemTypeWeights.put(ItemType.BIG_ROCK, 3);
            itemTypeWeights.put(ItemType.SMALL_ROCK, 1);
            itemTypeWeights.put(ItemType.MEDIUM_ROCK, 2);
            itemTypeWeights.put(ItemType.SMALL_STICK, 2);
            itemTypeWeights.put(ItemType.MEDIUM_STICK, 2);
            itemTypeWeights.put(ItemType.BIG_STICK, 3);
            itemTypeWeights.put(ItemType.NETTLE, 4);
        }
        else {
            itemTypeWeights.put(ItemType.BOOK, 4);
            itemTypeWeights.put(ItemType.BOTTLE, 2);
            itemTypeWeights.put(ItemType.FORK, 1);
            itemTypeWeights.put(ItemType.KNIFE, 1);
            itemTypeWeights.put(ItemType.GLASS, 2);
            itemTypeWeights.put(ItemType.PAINTING, 3);
            itemTypeWeights.put(ItemType.VASE, 3);
            itemTypeWeights.put(ItemType.LAMP, 4);
            itemTypeWeights.put(ItemType.TABLE, 2);
            itemTypeWeights.put(ItemType.CHAIR, 3);
        }

    }

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


     */

}

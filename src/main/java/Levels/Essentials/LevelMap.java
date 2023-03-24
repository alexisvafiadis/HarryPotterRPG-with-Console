package Levels.Essentials;

import Characters.Character;
import Characters.Wizard;
import Console.InputParser;
import Items.Item;

import java.util.HashMap;

public class LevelMap {
    private int width;
    private int height;
    private char[][] grid;

    public LevelMap(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new char[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void setTile(int x, int y, char tile) {
        if (isPositionPossible(x,y)) {
            grid[y][x] = tile;
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
    }

    public char getTile(int x, int y) {
        if (isPositionPossible(x,y)) {
            return grid[y][x];
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public boolean isPositionPossible(int x, int y) {
        return ((x >= 0 && x < width && y >= 0 && y < height));
    }

    public double calculateDistance(Character character, Item item) {
        return Math.sqrt(Math.pow((item.getPositionX() - character.getPositionX()), 2) + Math.pow((item.getPositionY() - character.getPositionY()), 2));
    }

    public double calculateDistance(Character character1, Character character2) {
        return Math.sqrt(Math.pow((character1.getPositionX() - character2.getPositionX()), 2) + Math.pow((character1.getPositionY() - character2.getPositionY()), 2));
    }
}

package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public enum MapPool {
    CASTLE("src/main/resources/img/blocks/grass.png", "src/main/resources/img/blocks/grey.png",
            new Cell(1, 10), new Cell(10, 1),
            Arrays.asList(new Cell(2,2), new Cell(2,4), new Cell(2,5), new Cell(2,8), new Cell(2,9),
                    new Cell(1,8), new Cell(4,2), new Cell(4,7), new Cell(4,8), new Cell(4,6),
                    new Cell(4,10), new Cell(5,8), new Cell(7,9), new Cell(6,4), new Cell(7,1),
                    new Cell(7,4), new Cell(7,5), new Cell(7,6), new Cell(9,2), new Cell(9,3),
                    new Cell(9,6), new Cell(9,7), new Cell(9,9), new Cell(10,3))),

    SAND("src/main/resources/img/blocks/sand.png", "src/main/resources/img/blocks/brown.png",
            new Cell(9, 10), new Cell(2, 1),
            Arrays.asList(new Cell(3,1), new Cell(8,1), new Cell(2,2), new Cell(4,3), new Cell(5,3),
                    new Cell(7,3), new Cell(9,3), new Cell(10,3), new Cell(4,4), new Cell(7,4),
                    new Cell(9,4), new Cell(4,5), new Cell(7,5), new Cell(9,5), new Cell(2,6),
                    new Cell(4,6), new Cell(7,6), new Cell(2,7), new Cell(4,7), new Cell(7,7),
                    new Cell(1,8), new Cell(2,8), new Cell(4,8), new Cell(6,8), new Cell(7,8),
                    new Cell(9,9), new Cell(3,10), new Cell(8,10))),

    NEON("src/main/resources/img/blocks/blue.png", "src/main/resources/img/blocks/pink.png",
            new Cell(9, 1), new Cell(2, 10),
            Arrays.asList(new Cell(4,1), new Cell(10,1), new Cell(2,2), new Cell(5,2), new Cell(7,2),
                    new Cell(9,2), new Cell(3,3), new Cell(8,3), new Cell(4,4), new Cell(6,4),
                    new Cell(7,4), new Cell(1,5), new Cell(4,5), new Cell(10,5), new Cell(1,6),
                    new Cell(7,6), new Cell(10,6), new Cell(4,7), new Cell(5,7), new Cell(7,7),
                    new Cell(3,8), new Cell(8,8), new Cell(2,9), new Cell(4,9), new Cell(6,9),
                    new Cell(9,9), new Cell(1,10), new Cell(7,10))),

    JUNGLE("src/main/resources/img/blocks/grass.png", "src/main/resources/img/blocks/green.png",
            new Cell(10, 6), new Cell(1, 4),
            Arrays.asList(new Cell(2,1), new Cell(3,1), new Cell(7,1), new Cell(9,1), new Cell(5,2),
                    new Cell(6,2), new Cell(7,2), new Cell(1,3), new Cell(2,3), new Cell(4,3),
                    new Cell(5,3), new Cell(9,3), new Cell(2,4), new Cell(7,4), new Cell(8,4),
                    new Cell(9,4), new Cell(2,5), new Cell(3,5), new Cell(4,5), new Cell(7,5),
                    new Cell(9,5), new Cell(4,6), new Cell(6,6), new Cell(7,6), new Cell(9,6),
                    new Cell(2,7), new Cell(6,7), new Cell(9,7), new Cell(10,7), new Cell(2,8),
                    new Cell(3,8), new Cell(4,8), new Cell(6,8), new Cell(2,9), new Cell(6,9),
                    new Cell(7,9), new Cell(9,9), new Cell(9,10)));

    private final String bgSkin;
    private final String borderSkin;
    private List<Cell> blockIndexes;
    private final Cell spawnOne;
    private final Cell spawnTwo;

    MapPool(String bgSkin, String borderSkin, Cell spawnOne, Cell spawnTwo, List<Cell> blockIndexes) {
        this.bgSkin = bgSkin;
        this.borderSkin = borderSkin;
        this.spawnOne = spawnOne;
        this.spawnTwo = spawnTwo;
        this.blockIndexes = blockIndexes;
        addBorders();
    }

    public void addBorders(){
        for(int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if ((i == 0 || j == 0) || (i == 11 || j == 11)) {
                    Cell cell = new Cell(i, j);
                    blockIndexes.add(cell);
                }
            }
        }
    }

    public String getBgSkin() {
        return bgSkin;
    }

    public String getBorderSkin() {
        return borderSkin;
    }

    public List<Cell> getBlockIndexes() {
        return blockIndexes;
    }

    public Cell getSpawnOne() {
        return spawnOne;
    }

    public Cell getSpawnTwo() {
        return spawnTwo;
    }
}

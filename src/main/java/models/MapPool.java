package models;

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
            new Cell(1, 10), new Cell(10, 1),
            Arrays.asList(new Cell(2,2), new Cell(2,4), new Cell(2,5), new Cell(2,8), new Cell(2,9),
                    new Cell(1,8), new Cell(4,2), new Cell(4,7), new Cell(4,8), new Cell(4,6),
                    new Cell(4,10), new Cell(5,8), new Cell(7,9), new Cell(6,4), new Cell(7,1),
                    new Cell(7,4), new Cell(7,5), new Cell(7,6), new Cell(9,2), new Cell(9,3),
                    new Cell(9,6), new Cell(9,7), new Cell(9,9), new Cell(10,3))),

    NEON("src/main/resources/img/blocks/blue.png", "src/main/resources/img/blocks/pink.png",
            new Cell(1, 10), new Cell(10, 1),
            Arrays.asList(new Cell(2,2), new Cell(2,4), new Cell(2,5), new Cell(2,8), new Cell(2,9),
                    new Cell(1,8), new Cell(4,2), new Cell(4,7), new Cell(4,8), new Cell(4,6),
                    new Cell(4,10), new Cell(5,8), new Cell(7,9), new Cell(6,4), new Cell(7,1),
                    new Cell(7,4), new Cell(7,5), new Cell(7,6), new Cell(9,2), new Cell(9,3),
                    new Cell(9,6), new Cell(9,7), new Cell(9,9), new Cell(10,3))),

    JUNGLE("src/main/resources/img/blocks/grass.png", "src/main/resources/img/blocks/green.png",
            new Cell(1, 10), new Cell(10, 1),
            Arrays.asList(new Cell(2,2), new Cell(2,4), new Cell(2,5), new Cell(2,8), new Cell(2,9),
                    new Cell(1,8), new Cell(4,2), new Cell(4,7), new Cell(4,8), new Cell(4,6),
                    new Cell(4,10), new Cell(5,8), new Cell(7,9), new Cell(6,4), new Cell(7,1),
                    new Cell(7,4), new Cell(7,5), new Cell(7,6), new Cell(9,2), new Cell(9,3),
                    new Cell(9,6), new Cell(9,7), new Cell(9,9), new Cell(10,3)));

    private final String bgSkin;
    private final String borderSkin;
    private final List<Cell> blockIndexes;
    private final Cell spawnOne;
    private final Cell spawnTwo;

    MapPool(String bgSkin, String borderSkin, Cell spawnOne, Cell spawnTwo, List<Cell> blockIndexes) {
        this.bgSkin = bgSkin;
        this.borderSkin = borderSkin;
        this.spawnOne = spawnOne;
        this.spawnTwo = spawnTwo;
        this.blockIndexes = blockIndexes;
    }
}

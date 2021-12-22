package models;

import java.util.List;

public class GameMap {
    private String bgSkin;
    private String borderSkin;
    private List<Cell> blockIndexes;
    private Cell spawnOne;
    private Cell spawnTwo;

    public Cell getSpawnOne() {
        return spawnOne;
    }

    public void setSpawnOne(Cell spawnOne) {
        this.spawnOne = spawnOne;
    }

    public Cell getSpawnTwo() {
        return spawnTwo;
    }

    public void setSpawnTwo(Cell spawnTwo) {
        this.spawnTwo = spawnTwo;
    }

    public String getBgSkin() {
        return bgSkin;
    }

    public void setBgSkin(String bgSkin) {
        this.bgSkin = bgSkin;
    }

    public String getBorderSkin() {
        return borderSkin;
    }

    public void setBorderSkin(String borderSkin) {
        this.borderSkin = borderSkin;
    }

    public List<Cell> getBlockIndexes() {
        return blockIndexes;
    }

    public void setBlockIndexes(List<Cell> blockIndexes) {
        this.blockIndexes = blockIndexes;
    }

    public void addEdges(List<Cell> edges){
        for(Cell cell : edges){
            this.getBlockIndexes().add(cell);
        }
    }
}

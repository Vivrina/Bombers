package models;

import protocol.MessageType;

import java.util.List;

public class GameMap {
    private MapPool map;

    private List<Cell> edges;

    public GameMap(MapPool map) {
        this.map = map;
        edges = map.getBlockIndexes();
    }

    public MapPool getMap() {
        return map;
    }

    public void setMap(MapPool map) {
        this.map = map;
    }

    public List<Cell> getEdges() {
        return edges;
    }

    public void setEdges(List<Cell> edges) {
        this.edges = edges;
    }

    public void addEdges(List<Cell> newEdges){
        for(Cell cell : newEdges){
            edges.add(cell);
        }
    }
}

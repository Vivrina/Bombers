package util;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import models.Cell;
import models.GameMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameUtils {
    private HBox gameHbox;
    private ArrayList<Cell> bombs;
    private GameMap gameMap;
    private GridPane gameTable;

    private static List<String> bombImages =
            Arrays.asList("src/main/resources/img/bombs/bomba1.png", "src/main/resources/img/bombs/bomba2.png",
                    "src/main/resources/img/bombs/bomba3.png", "src/main/resources/img/bombs/bomba4.png",
                    "src/main/resources/img/bombs/bomba5.png", "src/main/resources/img/bombs/bomba6.png",
                    "src/main/resources/img/bombs/bomba7.png", "src/main/resources/img/bombs/bomba8.png",
                    "src/main/resources/img/bombs/bomba9.png", "src/main/resources/img/bombs/bomba10.png");

    public GameUtils(GameMap gameMap, HBox gameHbox, ImageView player, ImageView enemy, GridPane gameTable, String role) {
        this.gameHbox = gameHbox;
        this.gameMap = gameMap;
        this.gameTable = gameTable;
        this.bombs = new ArrayList<>();
        create(player, enemy, role);
    }

    void create(ImageView player, ImageView enemy, String role){
        try {
        List<Cell> edges = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++) {
                Cell cell = new Cell(i, j);
                ImageView bg = new ImageView();
                bg.setImage(new Image((new FileInputStream(gameMap.getMap().getBgSkin()))));
                bg.setFitHeight(90.00);
                bg.setFitWidth(90.00);
                gameTable.add(bg, cell.getColumn(), cell.getRow());
                if(gameMap.getEdges().contains(cell)){
                    ImageView border = new ImageView();
                    border.setImage(new Image((new FileInputStream(gameMap.getMap().getBorderSkin()))));
                    border.setFitHeight(90.00);
                    border.setFitWidth(90.00);
                    gameTable.add(border, cell.getColumn(), cell.getRow());
                }
            }
        }

        Cell spawnPlayer = null;
        Cell spawnEnemy = null;
        if(role.equals("player1")){
            spawnPlayer = new Cell(gameMap.getMap().getSpawnOne().getColumn(), gameMap.getMap().getSpawnOne().getRow());
            spawnEnemy = new Cell(gameMap.getMap().getSpawnTwo().getColumn(), gameMap.getMap().getSpawnTwo().getRow());
        }else{
            spawnEnemy = new Cell(gameMap.getMap().getSpawnOne().getColumn(), gameMap.getMap().getSpawnOne().getRow());
            spawnPlayer = new Cell(gameMap.getMap().getSpawnTwo().getColumn(), gameMap.getMap().getSpawnTwo().getRow());
        }
        gameTable.add(player, spawnPlayer.getColumn(), spawnPlayer.getRow());

        gameTable.add(enemy, spawnEnemy.getColumn(), spawnEnemy.getRow());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gameHbox.setVisible(true);
        gameHbox.requestFocus();
        gameTable.requestFocus();
    }


    public void goLeft(ImageView gamer) {
        if(!gameMap.getMap().getBlockIndexes().contains(new Cell((gameTable.getColumnIndex(gamer) - 1), gameTable.getRowIndex(gamer)))) {
            gameTable.setColumnIndex(gamer, gameTable.getColumnIndex(gamer) - 1);
        }
    }

    public void goRight(ImageView gamer) {
        if(!gameMap.getMap().getBlockIndexes().contains(new Cell((gameTable.getColumnIndex(gamer) + 1), gameTable.getRowIndex(gamer)))) {
            gameTable.setColumnIndex(gamer, gameTable.getColumnIndex(gamer) + 1);
        }
    }

    public void goUp(ImageView gamer) {
        if(!gameMap.getMap().getBlockIndexes().contains(new Cell(gameTable.getColumnIndex(gamer), (gameTable.getRowIndex(gamer)-1)))) {
            gameTable.setRowIndex(gamer, gameTable.getRowIndex(gamer) - 1);
        }
    }

    public void goDown(ImageView gamer) {
        if(!gameMap.getMap().getBlockIndexes().contains(new Cell(gameTable.getColumnIndex(gamer), (gameTable.getRowIndex(gamer)+1)))) {
            gameTable.setRowIndex(gamer, gameTable.getRowIndex(gamer) + 1);
        }
    }

//    public void bomb(ImageView gamer, ImageView) {
//        Cell cell = new Cell(gameTable.getColumnIndex(gamer), gameTable.getRowIndex(gamer));
//        if (!bombs.contains(cell)) {
//            ImageView bomb = new ImageView();
//            try {
//                bomb.setImage(new Image((new FileInputStream(String.valueOf(bombImages.get(0))))));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            bomb.setFitHeight(90.00);
//            bomb.setFitWidth(90.00);
//            ImageView pl = gamer;
//            gameTable.getChildren().remove(gamer);
//            gameTable.add(bomb, cell.getColumn(), cell.getRow());
//            gameTable.add(pl, cell.getColumn(), cell.getRow());
//            startBombAnimation(bomb);
//        }
//    }
//
//    void startBombAnimation (ImageView bomb, ImageView player, ImageView enemy){
//        AtomicInteger i = new AtomicInteger(0);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), animation -> {
//            i.getAndIncrement();
//            try {
//                bomb.setImage(new Image(new FileInputStream(bombImages.get(i.get()))));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            if (i.get() == 7) {
//                if (checkDead(gameTable.getColumnIndex(player), gameTable.getRowIndex(player),
//                        gameTable.getColumnIndex(bomb), gameTable.getRowIndex(bomb))) {
//                    gameTable.getChildren().remove(player);
//                }
//                if (checkDead(gameTable.getColumnIndex(enemy), gameTable.getRowIndex(enemy),
//                        gameTable.getColumnIndex(bomb), gameTable.getRowIndex(bomb))) {
//                    gameTable.getChildren().remove(enemy);
//                }
//            }
//
//            if (i.get() == 9) {
//                gameTable.getChildren().remove(bomb);
//            }
//        }));
//        timeline.setCycleCount(9);
//        timeline.play();
//    }
//
//    boolean checkDead(int x1, int y1, int x2, int y2){
//        if((x1==x2 && y1==y2) || (x1+1==x2 && y1==y2) || (x1-1==x2 && y1==y2) || (x1==x2 && y1+1==y2) || (x1==x2 && y1-1==y2)){
//            return true;
//        }
//        return false;
//    }
}

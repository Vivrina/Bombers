package util;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import models.Cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameUtils {
    private ArrayList<Cell> edges;
    private ArrayList<Cell> bombs;
    private ImageView player;
    private ImageView enemy;
    private GridPane gameTable;

    private static List<String> bombImages =
            Arrays.asList("src/main/resources/img/bombs/bomba1.png", "src/main/resources/img/bombs/bomba2.png",
                    "src/main/resources/img/bombs/bomba3.png", "src/main/resources/img/bombs/bomba4.png",
                    "src/main/resources/img/bombs/bomba5.png", "src/main/resources/img/bombs/bomba6.png",
                    "src/main/resources/img/bombs/bomba7.png", "src/main/resources/img/bombs/bomba8.png",
                    "src/main/resources/img/bombs/bomba9.png", "src/main/resources/img/bombs/bomba10.png");

    public GameUtils(ArrayList<Cell> edges, ImageView player, ImageView enemy, GridPane gameTable) {
        this.edges = edges;
        this.player = player;
        this.enemy = enemy;
        this.gameTable = gameTable;
        this.bombs = new ArrayList<>();
    }

    public void setEnemy(ImageView enemy) {
        this.enemy = enemy;
    }

    public void setPlayer(ImageView player) {
        this.player = player;
    }

    public ImageView goLeft(ImageView gamer) {
        if(!edges.contains(new Cell((GridPane.getColumnIndex(gamer) - 1), GridPane.getRowIndex(gamer)))) {
            GridPane.setColumnIndex(gamer, GridPane.getColumnIndex(gamer) - 1);
        }
        return gamer;
    }

    public ImageView goRight(ImageView gamer) {
        if(!edges.contains(new Cell((GridPane.getColumnIndex(gamer) + 1), GridPane.getRowIndex(gamer)))) {
            GridPane.setColumnIndex(gamer, GridPane.getColumnIndex(gamer) + 1);
        }
        return gamer;
    }

    public ImageView goUp(ImageView gamer) {
        if(!edges.contains(new Cell(GridPane.getColumnIndex(gamer), (GridPane.getRowIndex(gamer)-1)))) {
            GridPane.setRowIndex(gamer, GridPane.getRowIndex(gamer) - 1);
        }
        return gamer;
    }

    public ImageView goDown(ImageView gamer) {
        if(!edges.contains(new Cell(GridPane.getColumnIndex(gamer), (GridPane.getRowIndex(gamer)+1)))) {
            GridPane.setRowIndex(gamer, GridPane.getRowIndex(gamer) + 1);
        }
        return gamer;
    }

    public void bomb(ImageView gamer) {
        Cell cell = new Cell(GridPane.getColumnIndex(gamer), GridPane.getRowIndex(gamer));
        if (!bombs.contains(cell)) {
            ImageView bomb = new ImageView();
            try {
                bomb.setImage(new Image((new FileInputStream(String.valueOf(bombImages.get(0))))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bomb.setFitHeight(90.00);
            bomb.setFitWidth(90.00);
            ImageView pl = gamer;
            gameTable.getChildren().remove(gamer);
            gameTable.add(bomb, cell.getColumn(), cell.getRow());
            gameTable.add(pl, cell.getColumn(), cell.getRow());
            startBombAnimation(bomb);
        }
    }

    void startBombAnimation (ImageView bomb){
        AtomicInteger i = new AtomicInteger(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), animation -> {
            i.getAndIncrement();
            try {
                bomb.setImage(new Image(new FileInputStream(bombImages.get(i.get()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (i.get() == 7) {
                if (checkDead(GridPane.getColumnIndex(player), GridPane.getRowIndex(player),
                        GridPane.getColumnIndex(bomb), GridPane.getRowIndex(bomb))) {
                    gameTable.getChildren().remove(player);
                }
                if (checkDead(GridPane.getColumnIndex(enemy), GridPane.getRowIndex(enemy),
                        GridPane.getColumnIndex(bomb), GridPane.getRowIndex(bomb))) {
                    gameTable.getChildren().remove(enemy);
                }
            }

            if (i.get() == 9) {
                gameTable.getChildren().remove(bomb);
            }
        }));
        timeline.setCycleCount(9);
        timeline.play();
    }

    boolean checkDead(int x1, int y1, int x2, int y2){
        if((x1==x2 && y1==y2) || (x1+1==x2 && y1==y2) || (x1-1==x2 && y1==y2) || (x1==x2 && y1+1==y2) || (x1==x2 && y1-1==y2)){
            return true;
        }
        return false;
    }
}

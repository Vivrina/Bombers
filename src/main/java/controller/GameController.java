package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.Cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable {
    private static String brown = "src/main/resources/img/play/brown.png";
    private static String grey = "src/main/resources/img/play/grey.png";
    private static String empty = "src/main/resources/img/play/empty.png";
    private static String bombPath = "src/main/resources/img/bombs/bomba1.png";

    private static List<String> bombImages =
            Arrays.asList("src/main/resources/img/bombs/bomba1.png", "src/main/resources/img/bombs/bomba2.png",
                    "src/main/resources/img/bombs/bomba3.png", "src/main/resources/img/bombs/bomba4.png",
                    "src/main/resources/img/bombs/bomba5.png", "src/main/resources/img/bombs/bomba6.png",
                    "src/main/resources/img/bombs/bomba7.png", "src/main/resources/img/bombs/bomba8.png",
                    "src/main/resources/img/bombs/bomba9.png", "src/main/resources/img/bombs/bomba10.png");

    private ArrayList<Cell> edges = new ArrayList<>();
    private ArrayList<Cell> bombs = new ArrayList<>();

    private static List<Cell> greyBlocks =
            Arrays.asList(new Cell(2,2), new Cell(2,4), new Cell(2,5), new Cell(2,8), new Cell(2,9),
                    new Cell(1,8), new Cell(4,2), new Cell(4,7), new Cell(4,8), new Cell(4,6),
                    new Cell(4,10), new Cell(5,8), new Cell(7,9), new Cell(6,4), new Cell(7,1),
                    new Cell(7,4), new Cell(7,5), new Cell(7,6), new Cell(9,2), new Cell(9,3),
                    new Cell(9,6), new Cell(9,7), new Cell(9,9), new Cell(10,3));

    @FXML
    public AnchorPane gameZone;

    @FXML
    public HBox game;


    @FXML
    public GridPane gameTable;


    private ImageView player1 = new ImageView();
    private ImageView player2 = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            gameZone.requestFocus();
//            gameBG.setImage(new Image(new FileInputStream("src/main/resources/img/gameBG.jpg")));

            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 12; j++) {
                    ImageView emptyBlock = new ImageView();
                    emptyBlock.setImage(new Image((new FileInputStream(empty))));
                    emptyBlock.setFitHeight(90.00);
                    emptyBlock.setFitWidth(90.00);
                    gameTable.add(emptyBlock, i, j);
                    if((i==0 || j==0) || (i==11 || j==11)) {
                        ImageView greyBlock = new ImageView();
                        greyBlock.setImage(new Image((new FileInputStream(grey))));
                        greyBlock.setFitHeight(90.00);
                        greyBlock.setFitWidth(90.00);
                        gameTable.add(greyBlock, i, j);
                        edges.add(new Cell(i, j));
                    }
                }
            }

            for(Cell cell : greyBlocks){
                ImageView greyBlock = new ImageView();
                greyBlock.setImage(new Image((new FileInputStream(grey))));
                greyBlock.setFitHeight(90.00);
                greyBlock.setFitWidth(90.00);
                gameTable.add(greyBlock, cell.getColumn(), cell.getRow());
                edges.add(cell);
            }

            player1.setImage(new Image(new FileInputStream("src/main/resources/img/cat/fat1.png")));
            player1.setFitHeight(90.00);
            player1.setFitWidth(90.00);
            gameTable.add(player1, 10, 1);

            player2.setImage(new Image(new FileInputStream("src/main/resources/img/cat/fat2.png")));
            player2.setFitHeight(90.00);
            player2.setFitWidth(90.00);
            gameTable.add(player2, 1, 10);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final EventHandler<KeyEvent> playerControlEvent = event -> {
        System.out.println(event.getCode());
        switch (event.getCode()) {
            case W: {
                if(!edges.contains(new Cell(GridPane.getColumnIndex(player1), (GridPane.getRowIndex(player1)-1)))) {
                    GridPane.setRowIndex(player1, GridPane.getRowIndex(player1) - 1);
                }
                break;
            }
            case A: {
                if(!edges.contains(new Cell((GridPane.getColumnIndex(player1) - 1), GridPane.getRowIndex(player1)))) {
                    GridPane.setColumnIndex(player1, GridPane.getColumnIndex(player1) - 1);
                }
                break;
            }
            case S: {
                if(!edges.contains(new Cell(GridPane.getColumnIndex(player1), (GridPane.getRowIndex(player1)+1)))) {
                    GridPane.setRowIndex(player1, GridPane.getRowIndex(player1) + 1);
                }
                break;
            }
            case D: {
                if(!edges.contains(new Cell((GridPane.getColumnIndex(player1) + 1), GridPane.getRowIndex(player1)))) {
                    GridPane.setColumnIndex(player1, GridPane.getColumnIndex(player1) + 1);
                }
                break;
            }
            case ENTER: {
                Cell cell = new Cell(GridPane.getColumnIndex(player1), GridPane.getRowIndex(player1));
                if(!bombs.contains(cell)) {
                    ImageView bomb = new ImageView();
                    try {
                        bomb.setImage(new Image((new FileInputStream(String.valueOf(bombPath)))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bomb.setFitHeight(90.00);
                    bomb.setFitWidth(90.00);
                    ImageView playerCopy = player1;
                    gameTable.getChildren().remove(player1);
                    gameTable.add(bomb, cell.getColumn(), cell.getRow());
                    gameTable.add(playerCopy, cell.getColumn(), cell.getRow());
                    player1 = playerCopy;
                    startBombAnimation(bomb);
                }
                break;
            }

        }
    };

    void startBombAnimation(ImageView bomb) {
        AtomicInteger i = new AtomicInteger(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), animation -> {
            i.getAndIncrement();
            try {
                bomb.setImage(new Image(new FileInputStream(bombImages.get(i.get()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(i.get() == 7){
                if(checkDead(GridPane.getColumnIndex(player1), GridPane.getRowIndex(player1),
                        GridPane.getColumnIndex(bomb), GridPane.getRowIndex(bomb))){
                    gameTable.getChildren().remove(player1);
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



    public EventHandler<KeyEvent> getPlayerControlEvent() {
        return playerControlEvent;
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.Cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController implements Initializable {
    private static String brown = "src/main/resources/img/play/brown.png";
    private static String grey = "src/main/resources/img/play/grey.png";
    private static String empty = "src/main/resources/img/play/empty.png";

    private static final List<Cell> greyBlocks =
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
                        greyBlocks.add(new Cell(i, j));
                    }
                }
            }

            for(Cell cell : greyBlocks){
                ImageView greyBlock = new ImageView();
                greyBlock.setImage(new Image((new FileInputStream(grey))));
                greyBlock.setFitHeight(90.00);
                greyBlock.setFitWidth(90.00);
                gameTable.add(greyBlock, cell.getColumn(), cell.getRow());
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
                if(!greyBlocks.contains(new Cell(GridPane.getColumnIndex(player1), (GridPane.getRowIndex(player1)-1)))) {
                    GridPane.setRowIndex(player1, GridPane.getRowIndex(player1) - 1);
                    break;
                }
            }
            case A: {
                if(!greyBlocks.contains(new Cell((GridPane.getColumnIndex(player1) - 1), GridPane.getRowIndex(player1)))) {
                    GridPane.setColumnIndex(player1, GridPane.getColumnIndex(player1) - 1);
                    break;
                }
            }
            case S: {
                if(!greyBlocks.contains(new Cell(GridPane.getColumnIndex(player1), (GridPane.getRowIndex(player1)+1)))) {
                    GridPane.setRowIndex(player1, GridPane.getRowIndex(player1) + 1);
                    break;
                }
            }
            case D: {
                if(!greyBlocks.contains(new Cell((GridPane.getColumnIndex(player1) + 1), GridPane.getRowIndex(player1)))) {
                    GridPane.setColumnIndex(player1, GridPane.getColumnIndex(player1) + 1);
                    break;
                }
            }

        }
    };

    public EventHandler<KeyEvent> getPlayerControlEvent() {
        return playerControlEvent;
    }
}

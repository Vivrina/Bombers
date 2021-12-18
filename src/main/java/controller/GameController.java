package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private static String brown = "src/main/resources/img/play/brown.png";
    private static String grey = "src/main/resources/img/play/grey.png";
    private static String empty = "src/main/resources/img/play/empty.png";

    private static final List<Pair<Integer, Integer>> greyBlocks =
            Arrays.asList(new Pair<>(2,2), new Pair<>(2,4), new Pair<>(2,5), new Pair<>(2,8), new Pair<>(2,9),
                    new Pair<>(1,8), new Pair<>(4,2), new Pair<>(4,7), new Pair<>(4,8), new Pair<>(4,6),
                    new Pair<>(4,10), new Pair<>(5,8), new Pair<>(7,9), new Pair<>(6,4), new Pair<>(7,1),
                    new Pair<>(7,4), new Pair<>(7,5), new Pair<>(7,6), new Pair<>(9,2), new Pair<>(9,3),
                    new Pair<>(9,6), new Pair<>(9,7), new Pair<>(9,9), new Pair<>(10,3));

    @FXML
    public HBox game;

    @FXML
    private ImageView gameBG;

    @FXML
    public GridPane gameTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            gameBG.setImage(new Image(new FileInputStream("src/main/resources/img/gameBG.jpg")));

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
                    }
                }
            }

            for(Pair<Integer, Integer> pair : greyBlocks){
                ImageView greyBlock = new ImageView();
                greyBlock.setImage(new Image((new FileInputStream(grey))));
                greyBlock.setFitHeight(90.00);
                greyBlock.setFitWidth(90.00);
                gameTable.add(greyBlock, pair.getKey(), pair.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

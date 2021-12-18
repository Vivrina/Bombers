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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private static String cur_image;
    private static String cur_map;
    private static String brown = "src/main/resources/img/play/brown.png";
    private static String grey = "src/main/resources/img/play/grey.png";
    private static String empty = "src/main/resources/img/play/empty.png";
    private static String green;

    @FXML
    public HBox game;

    @FXML
    private ImageView gameBG;

    @FXML
    public GridPane gameTable;

    @FXML
    private ImageView grey1;

    @FXML
    private ImageView grey2;

    @FXML
    private ImageView grey3;

    @FXML
    private ImageView grey4;

    @FXML
    private ImageView grey5;

    @FXML
    private ImageView grey6;

    @FXML
    private ImageView brown1;

    @FXML
    private ImageView brown2;

    @FXML
    private ImageView brown3;

    @FXML
    private ImageView brown4;

    @FXML
    private ImageView brown5;

    @FXML
    private ImageView brown6;

    @FXML
    private ImageView empty1;

    @FXML
    private ImageView empty2;

    @FXML
    private ImageView empty3;

    @FXML
    private ImageView empty4;

    @FXML
    private ImageView empty5;

    @FXML
    private ImageView empty6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        CatV.setVisible(false);
//        CatV.setSpacing(50);
//        CatH.setSpacing(200);
//        MapV.setVisible(false);
//        MapV.setSpacing(50);
//        MapH.setSpacing(200);
//        MapPV.setVisible(false);
//        MapPV.setSpacing(3);
//        MapPH1.setVisible(false);
//        MapPH1.setSpacing(3);
//
//        MapPH2.setVisible(false);
//        MapPH3.setVisible(false);
//        MapPH4.setVisible(false);
//        MapPH5.setVisible(false);
//        MapPH6.setVisible(false);
//        MapPH7.setVisible(false);
//        MapPH8.setVisible(false);
//        MapPH9.setVisible(false);

        try {
            gameBG.setImage(new Image(new FileInputStream("src/main/resources/img/gameBG.jpg")));
            grey1.setImage(new Image((new FileInputStream(grey))));
            grey2.setImage(new Image((new FileInputStream(grey))));
            grey3.setImage(new Image((new FileInputStream(grey))));
            grey4.setImage(new Image((new FileInputStream(grey))));
            grey5.setImage(new Image((new FileInputStream(grey))));
            grey6.setImage(new Image((new FileInputStream(grey))));
            brown1.setImage(new Image((new FileInputStream(brown))));
            brown2.setImage(new Image((new FileInputStream(brown))));
            brown3.setImage(new Image((new FileInputStream(brown))));
            brown4.setImage(new Image((new FileInputStream(brown))));
            brown5.setImage(new Image((new FileInputStream(brown))));
            brown6.setImage(new Image((new FileInputStream(brown))));
            empty1.setImage(new Image((new FileInputStream(empty))));
            empty2.setImage(new Image((new FileInputStream(empty))));
            empty3.setImage(new Image((new FileInputStream(empty))));
            empty4.setImage(new Image((new FileInputStream(empty))));
            empty5.setImage(new Image((new FileInputStream(empty))));
            empty6.setImage(new Image((new FileInputStream(empty))));

//            for(int i = 0; i < 10; i++){
//                for(int j = 0; j < 10; j++) {
//                    gameTable.add(empty1, i, j);
//                    if((i==0 || j==0) || (i==9 || j==9)) {
//                        gameTable.add(grey1, i, j);
//                    }
//                }
//            }
//            green = "src/main/resources/img/play/green0.png";
//
//
//            bg.setImage(new Image(new FileInputStream("src/main/resources/img/bg1.png")));
//            cat.setImage(new Image(new FileInputStream(cur_image)));
//            map.setImage(new Image(new FileInputStream(cur_map)));
//            brown2.setImage(new Image(new FileInputStream(brown)));
//            brown3.setImage(new Image(new FileInputStream(brown)));
//            brown4.setImage(new Image(new FileInputStream(brown)));
//            brown5.setImage(new Image(new FileInputStream(brown)));
//            green1.setImage(new Image(new FileInputStream(green)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

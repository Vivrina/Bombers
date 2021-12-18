package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static String brown;
    private static String green;

    @FXML
    public HBox game;

    @FXML
    private ImageView gameBG;

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
            brown = "src/main/resources/img/play/block.png";
            brown1.setImage(new Image((new FileInputStream(brown))));
            brown2.setImage(new Image((new FileInputStream(brown))));
            brown3.setImage(new Image((new FileInputStream(brown))));
            brown4.setImage(new Image((new FileInputStream(brown))));
            brown5.setImage(new Image((new FileInputStream(brown))));
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

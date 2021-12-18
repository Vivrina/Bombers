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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static String cur_image;
    private static String cur_map;
    private static String brown;
    private static String green;

    @FXML
    public Button mainButton;

    @FXML
    private Button rightButton;

    @FXML
    private Button leftButton;

    @FXML
    private VBox CatV;

    @FXML
    private HBox CatH;

    @FXML
    private VBox MapV;

    @FXML
    private HBox MapH;

    @FXML
    private ImageView bg;

    @FXML
    private ImageView cat;

    @FXML
    private ImageView map;

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
    private ImageView green1;

    @FXML
    private VBox MapPV;

    @FXML
    private HBox MapPH1;

    @FXML
    private HBox MapPH2;

    @FXML
    private HBox MapPH3;

    @FXML
    private HBox MapPH4;

    @FXML
    private HBox MapPH5;

    @FXML
    private HBox MapPH6;

    @FXML
    private HBox MapPH7;

    @FXML
    private HBox MapPH8;

    @FXML
    private HBox MapPH9;

    private static final List<String> cats =
            Arrays.asList("src/main/resources/img/cat/fat1.png", "src/main/resources/img/cat/fat2.png"
                    , "src/main/resources/img/cat/fat3.png", "src/main/resources/img/cat/fat4.png");

    private static final List<String> maps =
            Arrays.asList("src/main/resources/img/maps/map1.png", "src/main/resources/img/maps/map2.png"
                    , "src/main/resources/img/maps/map3.png", "src/main/resources/img/maps/map4.png");



    @FXML
    public void pressStartButton(ActionEvent event) throws Exception {
        bg.setImage(new Image(new FileInputStream("src/main/resources/img/bg2.png")));
        mainButton.setVisible(false);
        CatV.setVisible(true);
    }



    @FXML
    public void rightCharacter(ActionEvent event) throws Exception {
        int index = cats.indexOf(cur_image);
        if(index<cats.size()-1){
            index++;
            cur_image = cats.get(index);
            cat.setImage(new Image(new FileInputStream(cur_image)));
        }else{
            cur_image = cats.get(0);
            cat.setImage(new Image(new FileInputStream(cur_image)));
        }
    }

    @FXML
    public void leftCharacter(ActionEvent event) throws Exception {
        int index = cats.indexOf(cur_image);
        if(index>0){
            index--;
            cur_image = cats.get(index);
            cat.setImage(new Image(new FileInputStream(cur_image)));
        }else{
            cur_image = cats.get(cats.size()-1);
            cat.setImage(new Image(new FileInputStream(cur_image)));
        }
    }


    @FXML
    public void pressFirstReadyButton(ActionEvent event) throws Exception {
        bg.setImage(new Image(new FileInputStream("src/main/resources/img/bg3.png")));
        CatV.setVisible(false);
        MapV.setVisible(true);
    }

    @FXML
        public void rightMap(ActionEvent event) throws Exception {
        int index = maps.indexOf(cur_map);
        if(index<maps.size()-1){
            index++;
            cur_map = maps.get(index);
            map.setImage(new Image(new FileInputStream(cur_map)));
        }else{
            cur_map = maps.get(0);
            map.setImage(new Image(new FileInputStream(cur_map)));
        }
    }

    @FXML
    public void leftMap(ActionEvent event) throws Exception {
        int index = maps.indexOf(cur_map);
        if(index>0){
            index--;
            cur_map = maps.get(index);
            map.setImage(new Image(new FileInputStream(cur_map)));
        }else{
            cur_map = maps.get(maps.size()-1);
            map.setImage(new Image(new FileInputStream(cur_map)));
        }
    }

    @FXML
    public void pressTwoReadyButton (ActionEvent event) throws Exception{
        bg.setImage(new Image(new FileInputStream("src/main/resources/img/bg4.png")));
        MapV.setVisible(false);
        MapPV.setVisible(true);
        MapPH1.setVisible(true);
        MapPH2.setVisible(true);
        MapPH3.setVisible(true);
        MapPH4.setVisible(true);
        MapPH5.setVisible(true);
        MapPH6.setVisible(true);
        MapPH7.setVisible(true);
        MapPH8.setVisible(true);
        MapPH9.setVisible(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CatV.setVisible(false);
        CatV.setSpacing(50);
        CatH.setSpacing(200);
        MapV.setVisible(false);
        MapV.setSpacing(50);
        MapH.setSpacing(200);
        MapPV.setVisible(false);
        MapPV.setSpacing(3);
        MapPH1.setVisible(false);
        MapPH1.setSpacing(3);

        MapPH2.setVisible(false);
        MapPH3.setVisible(false);
        MapPH4.setVisible(false);
        MapPH5.setVisible(false);
        MapPH6.setVisible(false);
        MapPH7.setVisible(false);
        MapPH8.setVisible(false);
        MapPH9.setVisible(false);

        try {
            cur_image = "src/main/resources/img/cat/fat1.png";
            cur_map = "src/main/resources/img/maps/map1.png";
            brown = "src/main/resources/img/play/brown0.png";
            green = "src/main/resources/img/play/green0.png";


            bg.setImage(new Image(new FileInputStream("src/main/resources/img/bg1.png")));
            cat.setImage(new Image(new FileInputStream(cur_image)));
            map.setImage(new Image(new FileInputStream(cur_map)));
            brown1.setImage(new Image((new FileInputStream(brown))));
            brown2.setImage(new Image(new FileInputStream(brown)));
            brown3.setImage(new Image(new FileInputStream(brown)));
            brown4.setImage(new Image(new FileInputStream(brown)));
            brown5.setImage(new Image(new FileInputStream(brown)));
            green1.setImage(new Image(new FileInputStream(green)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

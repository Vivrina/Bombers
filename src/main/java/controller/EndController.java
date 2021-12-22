package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {

    @FXML
    public ImageView resultBG;

//    public void toTheMenu(ActionEvent event) {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
//        Node node=(Node) event.getSource();
//        Stage stage=(Stage) node.getScene().getWindow();
//        Parent root = null;
//        try {
//            root = fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.setFullScreen(true);
//        stage.show();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resultBG.setImage(new Image(new FileInputStream("src/main/resources/img/bg1.png")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void changeBG(String bg){
        try {
            resultBG.setImage(new Image(new FileInputStream(bg)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

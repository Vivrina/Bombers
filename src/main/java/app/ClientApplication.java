package app;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {


    private static final String FXML_FILE_NAME = "/fxml/Main.fxml";


    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream(FXML_FILE_NAME));
        Scene scene = new Scene(root);



        primaryStage.setTitle("Bombers");
//        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
//        MainController mainController = fxmlLoader.getController();
//        scene.setOnKeyPressed(mainController.getPlayerControlEvent());


        primaryStage.show();



    }
}

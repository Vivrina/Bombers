package controller;

import enums.Action;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.Cell;
import protocol.Message;
import protocol.MessageType;
import sockets.ClientSocket;
import util.GameUtils;

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


    private static List<Cell> greyBlocks =
            Arrays.asList(new Cell(2,2), new Cell(2,4), new Cell(2,5), new Cell(2,8), new Cell(2,9),
                    new Cell(1,8), new Cell(4,2), new Cell(4,7), new Cell(4,8), new Cell(4,6),
                    new Cell(4,10), new Cell(5,8), new Cell(7,9), new Cell(6,4), new Cell(7,1),
                    new Cell(7,4), new Cell(7,5), new Cell(7,6), new Cell(9,2), new Cell(9,3),
                    new Cell(9,6), new Cell(9,7), new Cell(9,9), new Cell(10,3));


    private ClientSocket clientSocket;

    private GameUtils gameUtils;

    private String playerCat;
    private String enemyCat;




    public void setPlayerCat(String playerCat) {
        try {
            getPlayer().setImage(new Image(new FileInputStream(playerCat)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getEnemyCat() {
        return enemyCat;
    }

    public void setEnemyCat(String enemyCat) {
        this.enemyCat = enemyCat;
    }

    @FXML
    public AnchorPane gameZone;

    @FXML
    public HBox game;

    private ImageView player = new ImageView();

    private ImageView enemy = new ImageView();

    public ImageView getPlayer() {
        return player;
    }

    public void setPlayer(ImageView player) {
        this.player = player;
    }

    public ImageView getEnemy() {
        return enemy;
    }

    public void setEnemy(ImageView enemy) {
        this.enemy = enemy;
    }

    @FXML
    public GridPane gameTable;

    @FXML
    private ScrollPane messagesArea1;

    @FXML
    private VBox messages1;

    @FXML
    private VBox messageControl1;

    @FXML
    private TextField messageText1;

    @FXML
    private Button sendMessageButton1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            gameZone.requestFocus();
            ArrayList<Cell> edges = new ArrayList<>();

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


            player.setImage(new Image(new FileInputStream("src/main/resources/img/cat/fat1.png")));
            player.setFitHeight(90.00);
            player.setFitWidth(90.00);
            gameTable.add(player, 10, 1);

            enemy.setImage(new Image(new FileInputStream("src/main/resources/img/cat/fat1.png")));
            enemy.setFitHeight(90.00);
            enemy.setFitWidth(90.00);
            gameTable.add(enemy, 1, 10);

            gameUtils = new GameUtils(edges, player, enemy, gameTable);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        clientSocket = new ClientSocket();
        clientSocket.connect(this, "nickname");
    }

    private final EventHandler<KeyEvent> playerControlEvent = event -> {
        System.out.println(event.getCode());
        switch (event.getCode()) {
            case W: {
                ImageView gamer = gameUtils.goUp(player);
                gameUtils.setPlayer(gamer);

                Message message = new Message();
                message.setType(MessageType.ACTION);
                message.setBody(Action.UP.getTitle());
                clientSocket.sendMessage(message);

                break;
            }
            case A: {
                ImageView gamer = gameUtils.goLeft(player);
                gameUtils.setPlayer(gamer);

                Message message = new Message();
                message.setType(MessageType.ACTION);
                message.setBody(Action.LEFT.getTitle());
                clientSocket.sendMessage(message);

                break;
            }
            case S: {
                ImageView gamer = gameUtils.goDown(player);
                gameUtils.setPlayer(gamer);

                Message message = new Message();
                message.setType(MessageType.ACTION);
                message.setBody(Action.DOWN.getTitle());
                clientSocket.sendMessage(message);

                break;
            }
            case D: {
                ImageView gamer = gameUtils.goRight(player);
                gameUtils.setPlayer(gamer);

                Message message = new Message();
                message.setType(MessageType.ACTION);
                message.setBody(Action.RIGHT.getTitle());
                clientSocket.sendMessage(message);

                break;
            }
            case ENTER: {
                gameUtils.bomb(player);

                Message message = new Message();
                message.setType(MessageType.ACTION);
                message.setBody(Action.BOMB.getTitle());
                clientSocket.sendMessage(message);

                break;
            }

        }
    };


    private void sendSetup(String cat) {
        clientSocket.sendSetup(cat);
    }


    @FXML
    private void sendMessage() {
        String chatText = messageText1.getText();
        messageText1.clear();
        Message message = new Message();
        message.setType(MessageType.CHAT);
        message.setBody(chatText);
        clientSocket.sendMessage(message);
        Label label = new Label();
        //label.setStyle("-fx-font: Corier new");
        label.setText("Я: " + chatText);

        messages1.getChildren().add(label);
    }

    public VBox getMessages() {
        return messages1;
    }

    public EventHandler<KeyEvent> getPlayerControlEvent() {
        return playerControlEvent;
    }
}

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
import javafx.scene.layout.*;
import javafx.util.Duration;
import models.Cell;
import models.GameMap;
import models.MapPool;
import protocol.Message;
import protocol.MessageType;
import sockets.ClientSocket;
import util.GameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable {
    private ClientSocket clientSocket;

    private GameUtils gameUtils;

    private String playerCat;

    private String playerUsername;

    private String pickedMap;

    private String lobbyCode;

    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public void setPickedMap(String pickedMap) {
        this.pickedMap = pickedMap;
    }

    public void setPlayerCat(String playerCat) {
        this.playerCat = playerCat;
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

    @FXML
    public HBox waitingBlock;

    @FXML
    public ImageView loadingImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadingImage.setImage(new Image(new FileInputStream("src/main/resources/img/gif/loading.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        game.setVisible(false);
        waitingBlock.setVisible(true);
        gameZone.requestFocus();
    }

    public void doConnect(){
        clientSocket = new ClientSocket();
        clientSocket.connect(this, playerUsername);
    }

    public void doLobbyConnect(){
        Message message = new Message();
        message.setType(MessageType.LOBBY);
        message.addHeader("code", lobbyCode);
        message.addHeader("isReady", "false");
        message.addHeader("cat", playerCat);
        message.addHeader("map", pickedMap);
        clientSocket.sendMessage(message);
    }

    public void startGame(String code, String map, String role, String enemyCat, String enemyUsername){
        MapPool mapName = Enum.valueOf(MapPool.class , map);
        System.out.println(mapName);
        GameMap gameMap = new GameMap(mapName);
        waitingBlock.setVisible(false);
        GameUtils gameUtils = new GameUtils(gameMap, game, playerCat, enemyCat, gameTable, role);
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

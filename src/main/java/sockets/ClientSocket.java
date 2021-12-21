package sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.prism.PrImage;
import controller.GameController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import controller.MainController;
import protocol.Message;
import protocol.MessageType;
import util.GameUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket extends Thread {

    private Socket clientSocket;

    private final String HOST = "localhost";
    private final int PORT = 8888;

    private PrintWriter out;
    private BufferedReader fromServer;

    private GameController gameController;
    private GameUtils utils;

    public void connect(GameController gameController, String nickname) {
        try {
            this.gameController = gameController;
            clientSocket = new Socket(HOST, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Message message = new Message();
            message.setType(MessageType.CONNECT);
            message.addHeader("nickname", nickname);
            sendMessage(message);
            this.start();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void sendSetup(String cat) {
        Message message = new Message();
        message.setType(MessageType.SETUP);
        message.addHeader("cat", cat);
        sendMessage(message);
    }



    public void sendMessage(Message message) {
        try {
            String jsonMessage = new ObjectMapper().writeValueAsString(message);
            System.out.println(jsonMessage);
            out.println(jsonMessage);
        } catch (JsonProcessingException e) {
            //console log
        }
    }

    @Override
    public void run() {
        while (true) {
            String messageFromServer;
            Message message = null;
            try {
                messageFromServer = fromServer.readLine();
                System.out.println(messageFromServer);
                message = new ObjectMapper().readValue(messageFromServer, Message.class);
                System.out.println(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            switch (message.getType()) {
                case SETUP: {
                    String cat = message.getBody();
                    Platform.runLater(() -> gameController.setEnemyCat(cat));
                    break;
                }
                case CHAT: {
                    Label label = new Label();
                    label.setText(message.getBody());
                    label.setFont(Font.font("Arial"));
                    Platform.runLater(() -> gameController.getMessages().getChildren().add(label));
                    break;
                }
                case ACTION:
                    switch (message.getBody()) {
                        case("right"): {
                            ImageView enemy = gameController.getEnemy();
                            Platform.runLater(() -> utils.goRight(enemy));
                            Platform.runLater(() -> utils.setEnemy(enemy));
                            break;
                        }
                        case("left"): {
                            ImageView enemy = gameController.getEnemy();
                            Platform.runLater(() -> utils.goLeft(enemy));
                            Platform.runLater(() -> utils.setEnemy(enemy));;
                            break;
                        }
                        case("up"): {
                            ImageView enemy = gameController.getEnemy();
                            Platform.runLater(() -> utils.goUp(enemy));
                            Platform.runLater(() -> utils.setEnemy(enemy));
                            break;
                        }
                        case("down"): {
                            ImageView enemy = gameController.getEnemy();
                            Platform.runLater(() -> utils.goDown(enemy));
                            Platform.runLater(() -> utils.setEnemy(enemy));
                            break;
                        }
                        case("bomb"): {
                            ImageView enemy = gameController.getEnemy();
                            Platform.runLater(() -> utils.bomb(enemy));
                            break;
                        }
                    }
                    break;
            }
        }
    }
}

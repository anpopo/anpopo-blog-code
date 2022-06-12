package basic.of.javastudy.networkpractice;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClientExample extends Application {
    private Socket socket;

    void startClient() {
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress("localhost", 5001));  // blocking
                Platform.runLater(() -> {
                    displayText(String.format("[연결 완료: %s]", socket.getRemoteSocketAddress()));
                    btnCoon.setText("stop");
                    btnSend.setDisable(false);
                });
            } catch (Exception e) {
                Platform.runLater(() -> {displayText("[서버 통신 불가]");});
                if (null != socket && !socket.isClosed()) {
                    stopClient();
                }
                return;
            }
            receive();
        });

        thread.start();
    }

    void stopClient() {
        try {
            Platform.runLater(() -> {
                displayText("[연결 끊음]");
                btnCoon.setText("start");
                btnSend.setDisable(true);
            });

            if (null != socket && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {}
    }

    void receive() {
        while (true) {
            try {
                byte[] bytes = new byte[100];
                InputStream is = socket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                System.out.println("blocked");
                int readCount = bis.read(bytes);  // blocking
                System.out.println("unblocked");
                if (readCount == -1) {
                    throw new IOException();
                }

                String data = new String(bytes, 0, readCount, StandardCharsets.UTF_8);
                Platform.runLater(() -> displayText(String.format("[받기 완료] %s", data)));
            } catch (Exception e) {
                Platform.runLater(() -> displayText("[서버 통신 안됨]"));
                stopClient();
                break;
            }
        }
    }

    void send(String data) {
        Thread thread = new Thread(() -> {
            try {
                byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
                OutputStream os = socket.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os);
                bos.write(bytes);
                bos.flush();
                Platform.runLater(() -> displayText("[보내기 완료]"));
            } catch (Exception e) {
                Platform.runLater(() -> displayText("[서버 통신 안됨]"));
                stopClient();
            }
        });

        thread.start();
    }


    ////////////////////////////////////////////////////

    private TextArea txtDisplay;
    private TextField txtInput;
    private Button btnCoon;
    private Button btnSend;


    private void displayText(String text) {
        txtDisplay.appendText(text + "\n");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        txtDisplay = new TextArea();
        txtDisplay.setEditable(false);
        BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0));
        root.setCenter(txtDisplay);

        BorderPane bottom = new BorderPane();
        txtInput = new TextField();
        txtInput.setPrefSize(60, 30);
        BorderPane.setMargin(txtInput, new Insets(0, 1, 1, 1));

        btnCoon = new Button("start");
        btnCoon.setPrefSize(60, 30);
        btnCoon.setOnAction(e -> {
            if ("start".equalsIgnoreCase(btnCoon.getText())) {
                startClient();
            } else if ("stop".equalsIgnoreCase(btnCoon.getText())) {
                stopClient();
            }
        });

        btnSend = new Button("send");
        btnSend.setPrefSize(60, 30);
        btnSend.setDisable(true);
        btnSend.setOnAction(e -> send(txtInput.getText()));


        bottom.setCenter(txtInput);
        bottom.setLeft(btnCoon);
        bottom.setRight(btnSend);
        root.setBottom(bottom);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.setOnCloseRequest(e -> stopClient());
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

package basic.of.javastudy.networkpractice;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServerExample extends Application {

    // 스레드 풀 - 고정된 풀
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    // 서버 소켓 tcp 소켓 통신
    private ServerSocket serverSocket;
    // 연결된 클라이언트
    private final List<Client> connections = new Vector<>();

    void startServer() {

        // ServerSocket 생성 및 포트 바인딩
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));

        } catch (Exception e) {
            if (serverSocket != null && !serverSocket.isClosed()) {
                stopServer();
                return;
            }
        }

        // 연결 수락

        executorService.submit(
                () -> {
                    Platform.runLater(() -> {
                        displayText("[서버 시작]");
                        btnStartStop.setText("stop");
                    });

                    while (true) {
                        try {
                            assert serverSocket != null;
                            Socket socket = serverSocket.accept();  // blocking
                            String message = "[연결 수락 : " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
                            Platform.runLater(() -> displayText(message));

                            Client client = new Client(socket);
                            connections.add(client);

                            Platform.runLater(() -> displayText("연결 개수 : " + connections.size() + "]"));
                        } catch (IOException e) {
                            if (serverSocket != null && !serverSocket.isClosed()) {
                                stopServer();
                                break;
                            }
                        }
                    }

                }
        );
    }

    void stopServer() {
        try {
            for (Client client : connections) {
                client.getSocket().close();
            }
            connections.clear();

            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }

            Platform.runLater(() -> {
                displayText("[서버 종료]");
                btnStartStop.setText("start");
            });
        } catch (Exception e) {

        }
    }

    @Getter
    class Client {
        private Socket socket;

        public Client(Socket socket) {
            this.socket = socket;
            receive();
        }

        // 데이터 받기
        private void receive() {

            executorService.submit(
                    () -> {
                        try {
                            while (true) {
                                byte[] bytes = new byte[100];
                                InputStream is = socket.getInputStream();
                                BufferedInputStream bis = new BufferedInputStream(is);

                                // 클라이언트의 비정상 종료시
                                int readByteCount = bis.read(bytes);

                                // 클라이언트가 정상적으로 socket 의 close 를 요청한 경우
                                if (readByteCount == -1) {
                                    throw new IOException();
                                }

                                String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
                                Platform.runLater(() -> displayText(message));

                                String data = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
                                connections.forEach(client -> client.send(data));

                            }
                        } catch (Exception e) {
                            try {
                                connections.remove(Client.this);
                                String message = "[클라이언트 통신 불가: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
                                Platform.runLater(() -> displayText(message));
                                socket.close();
                            } catch (IOException ex) {
                            }
                        }
                    }
            );
        }

        // 데이터 보내기
        private void send(String data) {
            Runnable runnable = () -> {
                try {
                    byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
                    OutputStream os = socket.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    bos.write(bytes);
                    bos.flush();
                } catch (Exception e) {
                    try {
                        connections.remove(Client.this);
                        String message = "[클라이언트 통신 불가: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]";
                        Platform.runLater(() -> displayText(message));
                        socket.close();
                    } catch (IOException ex) {
                    }
                }
            };

            executorService.submit(runnable);
        }
    }

    ///////////////////////////////////////

    private TextArea txtDisplay;
    private Button btnStartStop;

    // UI 생성 코드
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        txtDisplay = new TextArea();
        txtDisplay.setEditable(false);
        BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0));
        root.setCenter(txtDisplay);


        btnStartStop = new Button("start");
        btnStartStop.setPrefHeight(30);
        btnStartStop.setMaxWidth(Double.MAX_VALUE);


        btnStartStop.setOnAction(e -> {
            if ("start".equalsIgnoreCase(btnStartStop.getText())) {
                startServer();
            } else if ("stop".equalsIgnoreCase(btnStartStop.getText())) {
                stopServer();
            }
        });

        root.setBottom(btnStartStop);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.setOnCloseRequest(e -> stopServer());
        primaryStage.show();
    }

    private void displayText(String text) {
        txtDisplay.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

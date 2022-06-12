package basic.of.javastudy.networkpractice;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerExample {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));

            while (true) {
                System.out.println("[연결 기다림]");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("[연결 수락함] " + isa.getHostName());

                InputStream inputStream = socket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                byte[] bytes = new byte[100];

                int readCount = bis.read(bytes);
                String message = new String(bytes, StandardCharsets.UTF_8);
                System.out.println("[데이터 받기 성공] : " + message);


                OutputStream outputStream = socket.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(outputStream);
                message = "Hello Client";
                bos.write(message.getBytes(StandardCharsets.UTF_8));
                bos.flush();
                System.out.println("[데이터 보내기 성공]");

                bos.close();
                outputStream.close();
                bis.close();
                inputStream.close();
                socket.close();
            }
        } catch (Exception e) {}

        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {}
        }
    }
}

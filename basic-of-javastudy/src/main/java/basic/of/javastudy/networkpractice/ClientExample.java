package basic.of.javastudy.networkpractice;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientExample {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket();
            System.out.println("[연결 요청]");
            socket.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("[연결 성공]");


            OutputStream os = socket.getOutputStream();
            String message = "Hello Server";
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

            os.write(bytes);
            os.flush();
            System.out.println("[데이터 보내기 성공]");

            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bytes = new byte[100];
            int readCount = bis.read(bytes);
            message = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("[데이터 받기 성공] : " + message);

            bis.close();
            inputStream.close();
            os.close();

        } catch (Exception e) {}

        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {}
        }
    }
}

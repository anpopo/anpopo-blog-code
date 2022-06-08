package step2.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WebServer {

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(ServerConfiguration.getServerConfigurationInstance().getServerAddress(), 0);

        httpServer.createContext("/greeting", exchange -> {
            String greetingMessage = ServerConfiguration.getServerConfigurationInstance().getGreetingMessage();
            exchange.sendResponseHeaders(200, greetingMessage.length());
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(greetingMessage.getBytes(StandardCharsets.UTF_8));
            responseBody.flush();
            responseBody.close();
        });

        httpServer.start();
    }
}

package step2.web;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ServerMain {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        initConfiguration();
        WebServer webServer = new WebServer();
        webServer.startServer();
    }

    public static void initConfiguration() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<ServerConfiguration> serverConfigurationClass = ServerConfiguration.class;

        Constructor<ServerConfiguration> declaredConstructor = serverConfigurationClass.getDeclaredConstructor(int.class, String.class);
        declaredConstructor.setAccessible(true);
        declaredConstructor.newInstance(8111, "hi this is anpopo practice of reflection");
    }
}

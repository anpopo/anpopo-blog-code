package dynamicproxy;


import dynamicproxy.external.DatabaseReader;
import dynamicproxy.external.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyMain {

    public static void main(String[] args) {
        List<String> list = createProxy(new ArrayList<>());

        list.add("hi");
        list.add("i am sehyeong");

        list.remove("hi");
    }


    public static void useHttpClient(HttpClient httpClient) {
        httpClient.initialize();

        String response = httpClient.sendRequest("some request");

        System.out.printf("Http response is : %s\n", response);
    }

    public static void useDatabaseReader(DatabaseReader databaseReader) throws InterruptedException {
        int rowsInGamesTable = databaseReader.countRowsInTable("GamesTable");

        System.out.printf("There are %d rows in GamesTable\n", rowsInGamesTable);
        String[] data = databaseReader.readRow("SELECT * FROM GamesTable");

        System.out.println(String.format("Received %s", String.join(", ", data)));

    }

    public static <T> T createProxy(Object originalObject) {
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();
        TimeMeasuringProxyHandler timeMeasuringProxyHandler = new TimeMeasuringProxyHandler(originalObject);

        return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), interfaces, timeMeasuringProxyHandler);
    }

    public static class TimeMeasuringProxyHandler implements InvocationHandler {

        private final Object originalObject;

        public TimeMeasuringProxyHandler(Object originalObject) {
            this.originalObject = originalObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Object result;

            System.out.println(String.format("Measuring Proxy - Before Executing method : %s()", method.getName()));

            long startTime = System.nanoTime();
            try {
                result = method.invoke(originalObject, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
            long endTime = System.nanoTime();

            System.out.println();
            System.out.println(String.format("Measuring Proxy - Execution of %s() took %dms", method.getName(), endTime - startTime));

            return result;
        }
    }

}

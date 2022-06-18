package method.invokation;

import method.invokation.database.DatabaseClient;
import method.invokation.http.HttpClient;
import method.invokation.logging.FileLogger;
import method.invokation.udp.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ReflectiveOperationException {
        DatabaseClient databaseClient = new DatabaseClient();
        HttpClient httpClient = new HttpClient("123.123.123.123");
        HttpClient httpClient1 = new HttpClient("127.0.0.1");
        FileLogger fileLogger = new FileLogger();
        UdpClient udpClient = new UdpClient();

        String requestBody = "request data";

        List<Class<?>> methodParameterTypes = Arrays.asList(new Class<?>[]{String.class});
        Map<Object, Method> requestExecutors = groupExecutors(Arrays.asList(databaseClient, httpClient, httpClient1, fileLogger, udpClient), methodParameterTypes);

        executeAll(requestExecutors, requestBody);
    }

    public static void executeAll(Map<Object, Method> requestExecutors, String requestBody) throws ReflectiveOperationException {

        for (Map.Entry<Object, Method> requestExecutorEntry : requestExecutors.entrySet()) {
            Object requestExecutor = requestExecutorEntry.getKey();
            Method method = requestExecutorEntry.getValue();

            method.invoke(requestExecutor, requestBody);
        }
    }

    public static Map<Object, Method> groupExecutors(List<Object> requestExecutors, List<Class<?>> methodParameterType) {
        HashMap<Object, Method> instanceToMethod = new HashMap<>();

        for (Object requestExecutor : requestExecutors) {
            Method[] allMethods = requestExecutor.getClass().getDeclaredMethods();

            for (Method method : allMethods) {
                if (Arrays.asList(method.getParameterTypes()).equals(methodParameterType)) {
                    instanceToMethod.put(requestExecutor, method);
                }
            }
        }
        return instanceToMethod;
    }

}

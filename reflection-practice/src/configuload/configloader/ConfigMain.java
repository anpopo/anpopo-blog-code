package configuload.configloader;

import configuload.data.GameConfig;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

public class ConfigMain {

    private static final Path GAME_CONFIG_PATH = Path.of("/Users/ansehyeong/Project/anpopo-blog-code/reflection-practice/resources/game-properties.cfg");
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameConfig gameConfig = createConfigObject(GameConfig.class, GAME_CONFIG_PATH);

        System.out.println(gameConfig);

    }

    public static <T> T createConfigObject(Class<T> clazz, Path filePath) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner sc = new Scanner(filePath);

        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T configInstance = constructor.newInstance();

        while (sc.hasNextLine()) {
            String configLine = sc.nextLine();

            String[] nameValuePair = configLine.split("=");
            String propertyName = nameValuePair[0];
            String propertyValue = nameValuePair[1];

            Field field;

            try {
                field = clazz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                System.out.printf("Property name : %s is unsupported\n", propertyName);
                continue;
            }

            field.setAccessible(true);
            Object parsedValue = parseValue(field.getType(), propertyValue);
            field.set(configInstance, parsedValue);
        }

        return configInstance;
    }

    private static Object parseValue(Class<?> type, String propertyValue) {
        if (type.equals(int.class)) {
            return Integer.parseInt(propertyValue);
        } else if (type.equals(short.class)) {
            return Short.parseShort(propertyValue);
        } else if (type.equals(long.class)) {
            return Long.parseLong(propertyValue);
        } else if (type.equals(double.class)) {
            return Double.parseDouble(propertyValue);
        } else if (type.equals(float.class)) {
            return Float.parseFloat(propertyValue);
        } else if (type.equals(String.class)) {
            return propertyValue;
        }

        throw new RuntimeException(String.format("Type %s is unsupported", type.getTypeName()));
    }

}

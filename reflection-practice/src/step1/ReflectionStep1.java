package step1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReflectionStep1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        Map<String, Integer> mapObject = new HashMap<>();

        Class<? extends Map> mapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("step1.ReflectionStep1$Square");

        var circleObject = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(stringClass, mapClass, squareClass);
        printClassInfo(Collection.class, boolean.class, int[][].class, Color.class, circleObject.getClass());
    }


    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.printf("class name : %s, class package name : %s\n", clazz.getSimpleName(), clazz.getPackageName());

            Class<?>[] interfaces = clazz.getInterfaces();

            for (Class<?> anInterface : interfaces) {
                System.out.printf("class %s implements : %s\n", clazz.getSimpleName(), anInterface.getSimpleName());
            }
            System.out.println("is Array : " + clazz.isArray());
            System.out.println("is Primitive : " + clazz.isPrimitive());
            System.out.println("is Enum : " + clazz.isEnum());
            System.out.println("is Interface : " + clazz.isInterface());
            System.out.println("is AnonymousClass : " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {
        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }


    private static interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN,
    }
}

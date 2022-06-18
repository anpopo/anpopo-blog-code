package arrayss;

import java.lang.reflect.Array;
import java.util.List;

public class ArrayMain {

    public static void main(String[] args) {
        int[] intArr = {1, 2, 3};

        double[][] twoDimensionArray = {{1.5, 2.5}, {3.5, 4.5}};

        List<Integer> integers = List.of(1, 2, 3, 4);

        inspectArrayObject(intArr);
        inspectArrayObject(twoDimensionArray);
        inspectArrayValues(intArr);
        inspectArrayValues(twoDimensionArray);

//        inspectArrayObject(integers);  // NPE

    }

    private static void inspectArrayValues(Object arrayObject) {
        int length = Array.getLength(arrayObject);

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < length; i++) {
            Object element = Array.get(arrayObject, i);

            if (element.getClass().isArray()) {
                inspectArrayValues(element);
            }
            sb.append(element.toString());

            if (i != length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        System.out.println(sb.toString());
    }

    private static void inspectArrayObject(Object arrayObject) {
        Class<?> clazz = arrayObject.getClass();

        System.out.printf("is array : %s\n", clazz.isArray());

        Class<?> componentType = clazz.getComponentType();

        System.out.printf("type of array : %s\n", componentType.getTypeName());
        System.out.printf("type of array : %s\n", componentType.getName());

    }
}

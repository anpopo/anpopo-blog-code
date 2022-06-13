package arrayss;

import java.lang.reflect.Array;

public class ArrayReader {

    public Object getArrayElement(Object array, int index) {
        int length = Array.getLength(array);

        if (index >= length) {
            return null;
        }

        if (index == -1) {
            return Array.get(array, length - 1);
        }
        return Array.get(array, index);
    }
}
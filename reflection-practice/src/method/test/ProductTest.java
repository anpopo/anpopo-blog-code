package method.test;

import method.api.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ProductTest {

    public static void main(String[] args) {
        testGetter(Product.class);
        testSetter(Product.class);

    }

    public static void testSetter(Class<?> dataClass) {
        List<Field> fields = getAllFields(dataClass);

        for (Field field : fields) {
            String setterName = "set" + capitalizeFirstLetter(field.getName());

            Method setterMethod = null;

            try {
                setterMethod = dataClass.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException();
            }

            if (!setterMethod.getReturnType().equals(void.class)) {
                throw new IllegalStateException();
            }
        }

    }

    public static void testGetter(Class<?> dataClass) {
        List<Field> fields = getAllFields(dataClass);

        Map<String, Method> methodNameToMethod = mapMethodNameToMethod(dataClass);
        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());

            if (!methodNameToMethod.containsKey(getterName)) {
                throw new IllegalStateException();
            }

            Method getter = methodNameToMethod.get(getterName);

            if (!getter.getReturnType().equals(field.getType())) {
                throw new IllegalStateException();
            }

            if (getter.getParameterCount() > 0) {
                throw new IllegalStateException();
            }
        }

    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (clazz == null || clazz.equals(Object.class)) {
            return Collections.EMPTY_LIST;
        }

        Field[] currentFields = clazz.getDeclaredFields();

        List<Field> inheritedFields = getAllFields(clazz.getSuperclass());

        List<Field> allFields = new ArrayList<>();

        allFields.addAll(Arrays.asList(currentFields));
        allFields.addAll(inheritedFields);

        return allFields;
    }

    private static String capitalizeFirstLetter(String name) {
        return name.substring(0, 1).toUpperCase(Locale.ROOT).concat(name.substring(1));
    }

    public static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
        Method[] methods = dataClass.getMethods();
        Map<String, Method> nameToMethod = new HashMap<>();

        for (Method method : methods) {
            nameToMethod.put(method.getName(), method);
        }

        return nameToMethod;
    }

}

package constructor.packageprivate.init;


import constructor.packageprivate.concrete.DependencyStart;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DependencyPractice {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        DependencyStart dependencyStart = createObjectRecursively(DependencyStart.class);
        dependencyStart.printDependency();

    }

    public static <T> T createObjectRecursively(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> firstConstructor = getFirstConstructor(clazz);
        System.out.printf("클래스 이름 : %s\n", clazz.getSimpleName());

        List<Object> constructorArgs = new ArrayList<>();

        for (Class<?> parameterType : firstConstructor.getParameterTypes()) {
            Object parameter = createObjectRecursively(parameterType);
            constructorArgs.add(parameter);
        }
        System.out.printf("클래스 %s 매개 변수 생성 완료\n", clazz.getSimpleName());

        firstConstructor.setAccessible(true);
        return (T) firstConstructor.newInstance(constructorArgs.toArray());
    }

    public static Constructor<?> getFirstConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            throw new IllegalStateException(String.format("클래스에 생성자가 존재하지 않습니다. 클래스 : %s", clazz.getSimpleName()));
        }

        return constructors[0];
    }
}

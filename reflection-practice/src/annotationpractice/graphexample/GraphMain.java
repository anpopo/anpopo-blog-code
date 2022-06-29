package annotationpractice.graphexample;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import static annotationpractice.graphexample.annotations.Annotations.*;

public class GraphMain {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        BestGamesFinder bestGamesFinder = new BestGamesFinder();
//        Set<String> games = bestGamesFinder.getAllGames();
//
//        Map<String, Float> gameToRating = bestGamesFinder.getGameToRating(games);
//        Map<String, Float> gameToPrice = bestGamesFinder.getGameToPrice(games);
//
//        SortedMap<Double, String> scoreToGame = bestGamesFinder.scoreGames(gameToPrice, gameToRating);
//
//        List<String> bestGamesInDescendingOrder = bestGamesFinder.getTopGames(scoreToGame);

//        List<String> bestGamesInDescendingOrder = execute(bestGamesFinder);
//        System.out.println(bestGamesInDescendingOrder);
//        System.out.println("===============================================");
//

        SqlQueryBuilder queryBuilder = new SqlQueryBuilder(Arrays.asList("1", "2", "3"), 10, "Movies", Arrays.asList("Id", "Name"));

        String sqlQuery = execute(queryBuilder);
        System.out.println(sqlQuery);
    }

    private static Map<String, Field> getInputToField(Class<?> clazz) {
        Map<String, Field> inputToField = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Input.class)) {
                continue;
            }

            Input input = clazz.getAnnotation(Input.class);
            inputToField.put(input.value(), field);
        }

        return inputToField;
    }

    private static <T> T execute(Object instance) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = instance.getClass();
        Map<String, Method> operationToMethod = getOperationToMethod(clazz);
        Map<String, Field> inputToField = getInputToField(clazz);

        Method finalResultMethod = findFinalResultMethod(clazz);

        return (T) executeWithDependencies(instance, finalResultMethod, operationToMethod, inputToField);
    }

    private static Object executeWithDependencies(Object instance, Method currentMethod, Map<String, Method> operationToMethod, Map<String, Field> inputToField) throws InvocationTargetException, IllegalAccessException {

        List<Object> parameterValues = new ArrayList<>(currentMethod.getParameterCount());

        for (Parameter parameter : currentMethod.getParameters()) {
            Object value = null;

            if (parameter.isAnnotationPresent(DependsOn.class)) {
                String dependencyOperationName = parameter.getAnnotation(DependsOn.class).value();
                Method dependencyMethod = operationToMethod.get(dependencyOperationName);

                value = executeWithDependencies(instance, dependencyMethod, operationToMethod, inputToField);
            } else if (parameter.isAnnotationPresent(Input.class)) {
                String inputName = parameter.getAnnotation(Input.class).value();

                Field field = inputToField.get(inputName);
                field.setAccessible(true);

                value = field.get(instance);
            }
            parameterValues.add(value);
        }
        return currentMethod.invoke(instance, parameterValues.toArray());
    }

    private static Map<String, Method> getOperationToMethod(Class<?> clazz) {
        Map<String, Method> operationNameToMethod = new HashMap<>();

        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (!declaredMethod.isAnnotationPresent(Operation.class)) {
                continue;
            }

            Operation annotation = declaredMethod.getAnnotation(Operation.class);
            operationNameToMethod.put(annotation.value(), declaredMethod);
        }
        return operationNameToMethod;
    }

    private static Method findFinalResultMethod(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(FinalResult.class)) {
                return method;
            }
        }

        throw new RuntimeException("No method found with FinalResult annotation");
    }
}

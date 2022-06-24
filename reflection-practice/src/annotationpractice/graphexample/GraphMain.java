package annotationpractice.graphexample;

import annotationpractice.graphexample.annotations.Annotations;

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

        List<String> bestGamesInDescendingOrder = execute(bestGamesFinder);
        System.out.println(bestGamesInDescendingOrder);
    }

    private static <T> T execute(Object instance) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = instance.getClass();
        Map<String, Method> operationToMethod = getOperationToMethod(clazz);

        Method finalResultMethod = findFinalResultMethod(clazz);

        return (T) executeWithDependencies(instance, finalResultMethod, operationToMethod);
    }

    private static Object executeWithDependencies(Object instance, Method currentMethod, Map<String, Method> operationToMethod) throws InvocationTargetException, IllegalAccessException {

        List<Object> parameterValues = new ArrayList<>(currentMethod.getParameterCount());

        for (Parameter parameter : currentMethod.getParameters()) {
            Object value = null;

            if (parameter.isAnnotationPresent(DependsOn.class)) {
                String dependencyOperationName = parameter.getAnnotation(DependsOn.class).value();
                Method dependencyMethod = operationToMethod.get(dependencyOperationName);

                value = executeWithDependencies(instance, dependencyMethod, operationToMethod);
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

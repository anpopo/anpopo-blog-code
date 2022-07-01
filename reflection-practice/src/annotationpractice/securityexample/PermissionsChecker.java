package annotationpractice.securityexample;

import annotationpractice.securityexample.internal.Annotations;
import annotationpractice.securityexample.internal.OperationType;
import annotationpractice.securityexample.internal.PermissionException;
import annotationpractice.securityexample.internal.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class PermissionsChecker {

    public static void checkPermissions(Object callerObject, String callerMethodName) throws Throwable {
        User user = getLoggedInUser(callerObject);
        Method callingMethod = getCallingMethod(callerObject, callerMethodName);
        Annotations.Permissions[] allPermissions = getClassAnnotatedPermissions(callerObject);
        Annotations.MethodOperations methodOperations = getCallerMethodOperations(callingMethod);

        OperationType[] methodOperationTypes = methodOperations.value();

        List<OperationType> userAllowedOperations = findUserAllowedOperations(allPermissions, user);

        for (OperationType methodOperationsTypes : methodOperationTypes) {
            if (!userAllowedOperations.contains(methodOperationsTypes)) {
                throw new PermissionException();
            }
        }
    }

    static List<OperationType> findUserAllowedOperations(Annotations.Permissions[] allPermissions, User user) {
        for (Annotations.Permissions permission : allPermissions) {
            if (permission.role().equals(user.getRole())) {
                return Arrays.asList(permission.allowed());
            }
        }
        return Collections.emptyList();
    }

    static Annotations.Permissions[] getClassAnnotatedPermissions(Object callerObject) {
        return callerObject.getClass().getAnnotationsByType(Annotations.Permissions.class);
    }

    static Annotations.MethodOperations getCallerMethodOperations(Method callerMethod) {
        return callerMethod.getAnnotation(Annotations.MethodOperations.class);
    }

//********************* Helper Methods ******************************/

    private static User getLoggedInUser(Object callerObject) throws NoSuchFieldException, IllegalAccessException {
        // DO NOT MODIFY THIS METHOD
        Class<?> callerClass = callerObject.getClass();

        Field userField = callerClass.getDeclaredField("user");

        userField.setAccessible(true);

        if (!userField.getType().equals(User.class)) {
            throw new IllegalStateException("The caller object must have a user field of type User");
        }

        return (User) userField.get(callerClass);
    }

    private static Method getCallingMethod(Object callerObject, String methodName) {
        // DO NOT MODIFY THIS METHOD
        return Arrays.stream(callerObject.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() 
                        -> new IllegalStateException(String.format("The passed method name :%s does not exist", methodName)));
    }
}
package step1;

import java.util.*;
public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
                Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");
                
    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();
        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
            .setInterface(inputClass.isInterface())
            .setEnum(inputClass.isEnum())
            .setName(inputClass.getSimpleName())
            .setJdk(isJdkClass(inputClass))
            .addAllInheritedClassNames(getAllInheritedClassNames(inputClass))
            .addAllImplementedInterfaces(findAllImplementedInterfaces(inputClass));

        return popupTypeInfo;
    }
    
    public static boolean isJdkClass(Class<?> inputClass) {
        return JDK_PACKAGE_PREFIXES.stream()
                .anyMatch(packagePrefix -> inputClass.getPackage() == null
                        || inputClass.getPackage().getName().startsWith(packagePrefix));
    }
    
    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        String[] inheritedClasses;
        if (inputClass.isInterface()) {  // 인터페이스 다중 구현 가능
            inheritedClasses = Arrays.stream(inputClass.getInterfaces())
                    .map(Class::getSimpleName)
                    .toArray(String[]::new);
        } else {  // 단일 상속 객체
            Class<?> inheritedClass = inputClass.getSuperclass();
            inheritedClasses = inheritedClass != null
                    ? new String[]{inputClass.getSuperclass().getSimpleName()}
                    : null;  // 원시값 포함
        }
        return inheritedClasses;
    }

    public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();

        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);
            allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
        }

        return allImplementedInterfaces;
    }
}
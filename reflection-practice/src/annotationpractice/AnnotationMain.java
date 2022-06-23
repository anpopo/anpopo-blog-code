package annotationpractice;

import annotationpractice.customannotation.InitializerClass;
import annotationpractice.customannotation.InitializerMethod;
import annotationpractice.customannotation.OpenResources;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnnotationMain {

    public static void main(String[] args) {

    }

    public static void initialize(String... packageNames) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> classes = getAllClasses(packageNames);

        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(InitializerClass.class)) {
                continue;
            }

            List<Method> methods = getAllInitializingMethods(clazz);

            Object instance = clazz.getConstructor().newInstance();

            for (Method method : methods) {
                method.invoke(instance);
            }
        }
    }

    private static List<Class<?>> getAllClasses(String[] packageNames) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for (String packageName : packageNames) {

            String packageRelativePath = packageName.replace('.', '/');

            URI packageUri = Main.class.getResource(packageRelativePath).toURI();

            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
            } else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());
                Path packageFullPathInJar = fileSystem.getPath(packageRelativePath);
                allClasses.addAll(getAllPackageClasses(packageFullPathInJar, packageName));

                fileSystem.close();
            }
        }
        return allClasses;
    }

    private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws IOException, ClassNotFoundException {
        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();
        for (Path file : files) {
            String fileName = file.getFileName().toString();

            if (fileName.endsWith(".class")) {
                String classFullName = String.format("%s.%s", packageName, fileName.replaceFirst("\\.class$", ""));
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }

        return classes;
    }

    private static List<Method> getAllInitializingMethods(Class<?> clazz) {
        List<Method> initializerMethods = new ArrayList<>();

        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(InitializerMethod.class)) {
                initializerMethods.add(declaredMethod);
            }
        }

        return initializerMethods;
    }


    public Set<Method> getAllAnnotatedMethods(Object input) {
        Set<Method> annotatedMethods = new HashSet<>();


        Class<?> clazz = input.getClass();

        Method[] allMethods = clazz.getDeclaredMethods();

        for (Method method : allMethods) {
            if (method.isAnnotationPresent(OpenResources.class)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }
}

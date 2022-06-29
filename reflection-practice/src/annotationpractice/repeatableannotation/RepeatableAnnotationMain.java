package annotationpractice.repeatableannotation;


import annotationpractice.repeatableannotation.annotations.Annotations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Annotations.RepeatableAnnotationPackageScan("loader")
public class RepeatableAnnotationMain {
    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {

        Class<?> aClass = Class.forName("annotationpractice.repeatableannotation.loader.Cache");

        System.out.println(aClass.getSimpleName());

        schedule();
    }

    private static void schedule() throws URISyntaxException, IOException, ClassNotFoundException {
        Annotations.RepeatableAnnotationPackageScan scanPackage = RepeatableAnnotationMain.class.getAnnotation(Annotations.RepeatableAnnotationPackageScan.class);

        if (scanPackage == null || scanPackage.value().length == 0) {
            return ;
        }

        List<Class<?>> allClasses = getAllClasses(scanPackage.value());

        List<Method> scheduledExecutorMethods = getScheduledExecutorMethods(allClasses);

        for (Method method : scheduledExecutorMethods) {
            scheduleMethodExecution(method);
        }
    }

    private static void scheduleMethodExecution(Method method) {
        Annotations.ExecuteOnSchedule[] schedules = method.getAnnotationsByType(Annotations.ExecuteOnSchedule.class);

        // 간단한 스케줄 스레드를 만들어서 백그라운드 스케줄링 처리에 사용
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        for (Annotations.ExecuteOnSchedule schedule : schedules) {
            scheduledExecutorService.scheduleAtFixedRate(
                    () -> runWhenScheduled(method),
                    schedule.delaySecond(),
                    schedule.periodSeconds(),
                    TimeUnit.SECONDS
            );

        }
    }

    private static void runWhenScheduled(Method method) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        System.out.printf("Executing at %s\n", dateFormat.format(currentDate));

        try {
            // static 메소드는 인스턴스 없이 실행하기 때문에 null 을 매개변수로 받을 수 있다.
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static List<Method> getScheduledExecutorMethods(List<Class<?>> allClasses) {
        List<Method> scheduledMethod = new ArrayList<>();

        for (Class<?> clazz : allClasses) {
            if (!clazz.isAnnotationPresent(Annotations.ScheduledExecutorClass.class)) {
                continue;
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getAnnotationsByType(Annotations.ExecuteOnSchedule.class).length != 0) {
                    scheduledMethod.add(method);
                }
            }
        }

        return scheduledMethod;
    }

    public static List<Class<?>> getAllClasses(String... packageNames) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace(".", "/");

            // nio 에 대한 이해도가 있어야 코드의 자세한 이해가 가능할 것 같다.
            URI packageUri = RepeatableAnnotationMain.class.getResource(packageRelativePath).toURI();
            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
            } else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());
                Path packageFullPath = fileSystem.getPath(packageRelativePath);
                allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
                fileSystem.close();
            }

        }

        return allClasses;
    }

    private static List<Class<?>> getAllPackageClasses(Path packageFullPath, String packageName) throws ClassNotFoundException, IOException {
        if (!Files.exists(packageFullPath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packageFullPath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();
        for (Path file : files) {
            String fileName = file.getFileName().toString();

            if (fileName.endsWith(".class")) {
                // 프로젝트의 main 이 아닌 하위 패키지를 생성해 실행했기 때문에 패키지 명을 하드코딩
                String classFullName = String.format("%s.%s.%s", "annotationpractice.repeatableannotation", packageName, fileName.replaceFirst("\\.class$", ""));
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }

        return classes;
    }

}

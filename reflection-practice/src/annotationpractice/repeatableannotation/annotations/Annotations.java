package annotationpractice.repeatableannotation.annotations;

import java.lang.annotation.*;

public class Annotations {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface RepeatableAnnotationPackageScan {
        String[] value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ScheduledExecutorClass {

    }


    /**
     * repeatable meta annotation
     */
    @Target(ElementType.METHOD)
    @Repeatable(ExecutionSchedules.class)
    public @interface ExecuteOnSchedule {
        int delaySecond() default 0;

        int periodSeconds();
    }

    /**
     * repeatable container annotation
     * repeatable meta annotation 으로 선언된 어노테이션의 배열을 가지고 있어야 한다
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExecutionSchedules {
        ExecuteOnSchedule[] value();
    }
}

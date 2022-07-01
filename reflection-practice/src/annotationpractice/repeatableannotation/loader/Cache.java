package annotationpractice.repeatableannotation.loader;


import annotationpractice.repeatableannotation.annotations.Annotations.ScheduledExecutorClass;

import static annotationpractice.repeatableannotation.annotations.Annotations.*;

@ScheduledExecutorClass
public class Cache {

    @ExecuteOnSchedule(periodSeconds = 5)
    @ExecuteOnSchedule(delaySecond = 10, periodSeconds = 1)
    public static void reloadCache() {
        System.out.println("Reloading cache");

    }
}

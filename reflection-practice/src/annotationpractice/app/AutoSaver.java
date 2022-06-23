package annotationpractice.app;

import annotationpractice.customannotation.InitializerClass;
import annotationpractice.customannotation.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThread() {
        System.out.println("Start automatic data saving to disk");
    }
}

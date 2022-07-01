package annotationpractice.initandretry.app;

import annotationpractice.initandretry.customannotation.InitializerClass;
import annotationpractice.initandretry.customannotation.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThread() {
        System.out.println("Start automatic data saving to disk");
    }
}

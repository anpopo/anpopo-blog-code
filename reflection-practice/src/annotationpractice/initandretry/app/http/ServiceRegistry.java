package annotationpractice.initandretry.app.http;

import annotationpractice.initandretry.customannotation.InitializerClass;
import annotationpractice.initandretry.customannotation.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService() {
        System.out.println("Service successfully registered");
    }
}

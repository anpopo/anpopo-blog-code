package annotationpractice.app.http;

import annotationpractice.customannotation.InitializerClass;
import annotationpractice.customannotation.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService() {
        System.out.println("Service successfully registered");
    }
}

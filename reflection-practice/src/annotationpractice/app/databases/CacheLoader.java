package annotationpractice.app.databases;

import annotationpractice.customannotation.InitializerClass;
import annotationpractice.customannotation.InitializerMethod;

@InitializerClass
public class CacheLoader {

    @InitializerMethod
    public void loadCache() {
        System.out.println("Loading data from cache");
    }

    public void reloadCache() {
        System.out.println("Reload cache");

    }
}

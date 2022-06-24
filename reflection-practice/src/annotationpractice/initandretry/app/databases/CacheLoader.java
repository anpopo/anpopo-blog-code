package annotationpractice.initandretry.app.databases;

import annotationpractice.initandretry.customannotation.InitializerClass;
import annotationpractice.initandretry.customannotation.InitializerMethod;

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

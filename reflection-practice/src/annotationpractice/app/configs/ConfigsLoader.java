package annotationpractice.app.configs;

import annotationpractice.customannotation.InitializerClass;
import annotationpractice.customannotation.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}

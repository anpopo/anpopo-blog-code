package annotationpractice.initandretry.app.configs;

import annotationpractice.initandretry.customannotation.InitializerClass;
import annotationpractice.initandretry.customannotation.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}

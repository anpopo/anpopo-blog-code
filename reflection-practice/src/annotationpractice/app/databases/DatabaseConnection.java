package annotationpractice.app.databases;

import annotationpractice.customannotation.InitializerClass;
import annotationpractice.customannotation.InitializerMethod;

@InitializerClass
public class DatabaseConnection {

    @InitializerMethod
    public void connectToDatabase1() {
        System.out.println("Connecting to database 1");
    }

    @InitializerMethod
    public void connectToDatabase2() {
        System.out.println("Connecting to database 2");
    }
}

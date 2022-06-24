package annotationpractice.initandretry.app.databases;

import annotationpractice.initandretry.customannotation.InitializerClass;
import annotationpractice.initandretry.customannotation.InitializerMethod;
import annotationpractice.initandretry.customannotation.RetryOperation;

import java.io.IOException;

@InitializerClass
public class DatabaseConnection {
    private int failCounter = 4;

    @RetryOperation(
            retryExceptions = IOException.class,
            durationBetweenRetriesMs = 1000,
            failureMessage = "Connection to database 1 failed after retires",
            numberOfRetries = 10
    )
    @InitializerMethod
    public void connectToDatabase1() throws IOException {
        System.out.println("Connecting to database 1");
        if (failCounter > 0) {
            failCounter--;
            throw new IOException("Connection failed");
        }

        System.out.println("Connection to database 1 succeeded");
    }

    @InitializerMethod
    public void connectToDatabase2() {
        System.out.println("Connecting to database 2");
    }
}

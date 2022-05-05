package basic.of.javastudy.absinter;

public class InterMain {
    public static void main(String[] args) {
        Attraction attraction = AttractionFactory.of("RollerCoaster");

        if (attraction != null) {
            attraction.startAttraction();
        }

        System.out.println("=============================================");

        attraction = AttractionFactory.of("Viking");

        if (attraction != null) {
            attraction.startAttraction();
        }
    }
}
